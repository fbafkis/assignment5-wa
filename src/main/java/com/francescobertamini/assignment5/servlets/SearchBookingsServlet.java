package com.francescobertamini.assignment5.servlets;

import com.francescobertamini.assignment5.ejb.SearchAccommodations;
import com.francescobertamini.assignment5.ejb.SearchBookings;
import com.francescobertamini.assignment5.entities.Accommodation;
import com.francescobertamini.assignment5.entities.ApartmentsBooking;
import com.francescobertamini.assignment5.entities.HotelsBooking;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "searchBookingsServlet", value = "searchBookings")
public class SearchBookingsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String guestName = request.getParameter("searchGuestName");

        Context ctx = null;
        SearchAccommodations searchAccommodations = null;
        SearchBookings searchBookings = null;
        try {
            ctx = new InitialContext();
            String name = "java:module/SearchAccommodationsBean!com.francescobertamini.assignment5.ejb.SearchAccommodations";
            String name2 = "java:module/SearchBookingsBean!com.francescobertamini.assignment5.ejb.SearchBookings";
            searchAccommodations = (SearchAccommodations) ctx.lookup(name);
            searchBookings = (SearchBookings) ctx.lookup(name2);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        ArrayList<ApartmentsBooking> apartmentsBookings = new ArrayList<>();
        ArrayList<HotelsBooking> hotelsBookings = new ArrayList<>();

        try {
            apartmentsBookings = searchBookings.searchApartmentBookings(guestName);
            hotelsBookings = searchBookings.searchHotelBookings(guestName);
        } catch (Exception e) {
            response.sendError(500);
            System.err.println(e);
        }

        JsonObject result = new JsonObject();
        JsonArray bookingsJSON = new JsonArray();

        for (int a = 0; a < apartmentsBookings.size(); a++) {
            Accommodation accommodation = new Accommodation();
            accommodation = searchAccommodations.getAccommodationById(apartmentsBookings.get(a).getApartment().getId());
            JsonObject apartmentBookingJSON = new JsonObject();

            apartmentBookingJSON.addProperty("id", apartmentsBookings.get(a).getBookingId());
            apartmentBookingJSON.addProperty("accName", accommodation.getName());
            apartmentBookingJSON.addProperty("arrival", apartmentsBookings.get(a).getArrival().toString());
            apartmentBookingJSON.addProperty("departure", apartmentsBookings.get(a).getDeparture().toString());
            apartmentBookingJSON.addProperty("type", "Apartment");
            bookingsJSON.add(apartmentBookingJSON);
        }

        for (int h = 0; h < hotelsBookings.size(); h++) {
            Accommodation accommodation = new Accommodation();
            accommodation = searchAccommodations.getAccommodationById(hotelsBookings.get(h).getHotel().getId());
            JsonObject hotelBookingJSON = new JsonObject();

            hotelBookingJSON.addProperty("id", hotelsBookings.get(h).getBookingId());
            hotelBookingJSON.addProperty("accName", accommodation.getName());
            hotelBookingJSON.addProperty("arrival", hotelsBookings.get(h).getArrival().toString());
            hotelBookingJSON.addProperty("departure", hotelsBookings.get(h).getDeparture().toString());
            hotelBookingJSON.addProperty("type", "Hotel");
            bookingsJSON.add(hotelBookingJSON);
        }

        result.add("bookings", bookingsJSON);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        out.print(result);
    }
}
