package com.francescobertamini.assignment5.servlets;

import com.francescobertamini.assignment5.ejb.BookHotel;
import com.francescobertamini.assignment5.ejb.SearchAccommodations;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "bookHotelServlet", value = "bookHotel")
public class BookHotelServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        long hotelId = Long.parseLong(request.getParameter("id"));
        LocalDate arrival = LocalDate.parse(request.getParameter("arrival"));
        LocalDate departure = LocalDate.parse(request.getParameter("departure"));
        String guestName = request.getParameter("hotelGuestName");
        int personsNumber = Integer.parseInt(request.getParameter("personsNumber"));

        boolean halfBoard;

        if (request.getParameterMap().containsKey("halfBoard")) {
            halfBoard = true;
        } else halfBoard = false;

        Context ctx = null;
        BookHotel bookHotel = null;

        try {
            ctx = new InitialContext();
            String name1 = "java:module/BookHotelBean!com.francescobertamini.assignment5.ejb.BookHotel";
            bookHotel = (BookHotel) ctx.lookup(name1);

        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            bookHotel.addBooking(hotelId, guestName, arrival, departure, personsNumber, halfBoard);
            response.setStatus(200);
        } catch (Exception e) {
            response.sendError(500);
            System.err.println(e);
        }
    }
}
