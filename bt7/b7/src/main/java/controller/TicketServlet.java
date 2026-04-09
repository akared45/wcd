package com.b7.controller;

import com.b7.model.Ticket;
import com.b7.utils.JPAUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import java.io.IOException;     
import java.time.LocalDate;    
import java.math.BigDecimal;    

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/ticket-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String event = request.getParameter("eventName");
        String customer = request.getParameter("customerName");
        String seat = request.getParameter("seatNumber");
        String dateStr = request.getParameter("date");
        String priceStr = request.getParameter("price");

        if (isAnyEmpty(event, customer, seat, dateStr, priceStr)) {
            request.setAttribute("error", "All fields are required!");
            doGet(request, response);
            return;
        }

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();

            Ticket ticket = new Ticket();
            ticket.setEventName(event);
            ticket.setCustomerName(customer);
            ticket.setSeatNumber(seat);
            ticket.setDate(LocalDate.parse(dateStr));
            ticket.setPrice(new BigDecimal(priceStr));

            em.persist(ticket);
            em.getTransaction().commit();

            response.sendRedirect(request.getContextPath() + "/list-tickets");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            request.setAttribute("error", "System error: " + e.getMessage());
            doGet(request, response);
        } finally {
            em.close();
        }
    }

    private boolean isAnyEmpty(String... fields) {
        for (String f : fields) {
            if (f == null || f.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}