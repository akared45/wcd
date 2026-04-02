package controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.JobPosting;
import util.JPAUtil;

@WebServlet("/jobs")
public class JobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                em.getTransaction().begin();
                JobPosting j = em.find(JobPosting.class, id);
                if (j != null)
                    em.remove(j);
                em.getTransaction().commit();
            }
            List<JobPosting> list = em.createQuery("SELECT j FROM JobPosting j", JobPosting.class).getResultList();
            request.setAttribute("jobList", list);
            request.getRequestDispatcher("/view/list-jobs.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String title = request.getParameter("title");
            String company = request.getParameter("company");
            String location = request.getParameter("location");
            String dateStr = request.getParameter("date_posted");

            if (title.length() < 5) {
                response.sendRedirect("add-job.jsp?err=1");
                return;
            }

            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            JobPosting jp = new JobPosting();
            jp.setTitle(title);
            jp.setCompany(company);
            jp.setLocation(location);
            jp.setDatePosted(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
            em.persist(jp);
            em.getTransaction().commit();
            em.close();
            response.sendRedirect("jobs?action=list");
        } catch (Exception e) {
            response.sendRedirect("add-job.jsp?err=2");
        }
    }
}