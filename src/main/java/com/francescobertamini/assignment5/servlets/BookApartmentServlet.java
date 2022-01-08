package com.francescobertamini.assignment5.servlets;

import com.francescobertamini.assignment5.ejb.BookApartment;
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

@WebServlet(name = "bookApartmentServlet", value = "bookApartment")
public class BookApartmentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        long apartmentId = Long.parseLong(request.getParameter("id"));
        LocalDate arrival = LocalDate.parse(request.getParameter("arrival"));
        LocalDate departure = LocalDate.parse(request.getParameter("departure"));
        String guestName = request.getParameter("apartmentGuestName");

        Context ctx = null;
        BookApartment bookApartment = null;
        SearchAccommodations searchAccommodations = null;

        try {
            ctx = new InitialContext();
            String name1 = "java:module/BookApartmentBean!com.francescobertamini.assignment5.ejb.BookApartment";
            bookApartment = (BookApartment) ctx.lookup(name1);
            String name2 = "java:module/SearchAccommodationsBean!com.francescobertamini.assignment5.ejb.SearchAccommodations";
            searchAccommodations = (SearchAccommodations) ctx.lookup(name2);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            //Apartment apartment = searchAccommodations.getApartmentById(apartmentId);
            bookApartment.addBooking(apartmentId, guestName, arrival, departure);
            response.setStatus(200);
        } catch (Exception e) {
            response.sendError(500);
            System.err.println(e);
        }
    }
}
