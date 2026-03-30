package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.text.html.parser.Entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JPAUtil;
import model.Event;
import java.util.List;

@WebServlet("/events")
public class EventServlet extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        String action = request.getParameter("action");
       
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                em.getTransaction().begin();
                Event e = em.find(Event.class, id);
                if (e != null)
                    em.remove(e);
                em.getTransaction().commit();
            }

            List<Event> list = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
            request.setAttribute("eventList", list);
            request.getRequestDispatcher("view-events.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String dateStr = request.getParameter("date");
        String venue = request.getParameter("venue");
        int seats = Integer.parseInt(request.getParameter("seats_available"));

        if (name.length() < 5 || seats <= 0) {
            response.sendRedirect("add-event.jsp?error=invalid");
            return;
        }

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Event event = new Event();
            event.setName(name);
            event.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
            event.setVenue(venue);
            event.setSeatsAvailable(seats);

            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
            response.sendRedirect("events");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("add-event.jsp?error=fail");
        } finally {
            em.close();
        }
    }
}
