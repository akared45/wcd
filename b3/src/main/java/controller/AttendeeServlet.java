package controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List; // Bắt buộc phải có
import model.Event;   // Import từ package model của bạn
import model.Attendee; // Import từ package model của bạn
import util.JPAUtil;
@WebServlet("/attendees")
public class AttendeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            if ("prepareAdd".equals(action)) {
                List<Event> events = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
                request.setAttribute("events", events);
                request.getRequestDispatcher("add-attendee.jsp").forward(request, response);
            } else if ("viewByEvent".equals(action)) {
                int eventId = Integer.parseInt(request.getParameter("eventId"));
                Event event = em.find(Event.class, eventId);
                List<Attendee> list = em.createQuery("SELECT a FROM Attendee a WHERE a.event.id = :eId", Attendee.class)
                        .setParameter("eId", eventId).getResultList();
                request.setAttribute("attendeeList", list);
                request.setAttribute("eventName", event.getName());
                request.getRequestDispatcher("view-attendees.jsp").forward(request, response);
            }
        } finally {
            em.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int eventId = Integer.parseInt(request.getParameter("event_id"));

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Event event = em.find(Event.class, eventId);
            Attendee a = new Attendee();
            a.setName(name);
            a.setEmail(email);
            a.setEvent(event);
            em.persist(a);
            em.getTransaction().commit();
            response.sendRedirect("attendees?action=viewByEvent&eventId=" + eventId);
        } finally {
            em.close();
        }
    }
}