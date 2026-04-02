package controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Book;
import model.Category;
import util.JPAUtil;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            switch (action) {
                case "list":
                    List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("list-books.jsp").forward(request, response);
                    break;

                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    em.getTransaction().begin();
                    Book b = em.find(Book.class, id);
                    if (b != null)
                        em.remove(b);
                    em.getTransaction().commit();
                    response.sendRedirect("books?action=list");
                    break;

                case "prepareInsert":
                    List<Category> cats = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
                    request.setAttribute("categories", cats);
                    request.getRequestDispatcher("insert-book.jsp").forward(request, response);
                    break;
            }
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String priceStr = request.getParameter("price");
        int catId = Integer.parseInt(request.getParameter("category_id"));

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Category cat = em.find(Category.class, catId);

            Book book = new Book();
            book.setTitle(title);
            book.setPrice(Double.parseDouble(priceStr));
            book.setCategory(cat);

            em.persist(book);
            em.getTransaction().commit();
            response.sendRedirect("books?action=list");
        } catch (Exception e) {
            response.sendRedirect("books?action=prepareInsert&error=1");
        } finally {
            em.close();
        }
    }
}