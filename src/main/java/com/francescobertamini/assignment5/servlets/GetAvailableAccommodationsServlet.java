package com.francescobertamini.assignment5.servlets;

import com.francescobertamini.assignment5.ejb.SearchAccommodations;
import com.francescobertamini.assignment5.entities.Accommodation;
import com.francescobertamini.assignment5.entities.Apartment;
import com.francescobertamini.assignment5.entities.Hotel;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@WebServlet(name = "getAvailableAccommodationsServlet", value = "getAvailableAccommodations")
public class GetAvailableAccommodationsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        int personsNumber = Integer.parseInt(request.getParameter("personsNumber"));
        LocalDate arrival = LocalDate.parse(request.getParameter("arrival"));
        LocalDate departure = LocalDate.parse(request.getParameter("departure"));
        long days = arrival.until(departure, ChronoUnit.DAYS);

        Context ctx = null;
        SearchAccommodations searchAccommodations = null;
        try {
            ctx = new InitialContext();
            String name = "java:module/SearchAccommodationsBean!com.francescobertamini.assignment5.ejb.SearchAccommodations";
            searchAccommodations = (SearchAccommodations) ctx.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        ArrayList<Apartment> apartments = new ArrayList<>();
        ArrayList<Hotel> hotels = new ArrayList<>();

        try {
            apartments = searchAccommodations.searchAvailableApartments(arrival, departure, days, personsNumber);
            hotels = searchAccommodations.searchAvailableHotels(arrival, departure, days, personsNumber);
        } catch (Exception e) {
            response.sendError(500);
            System.err.println(e);
        }

        JsonObject result = new JsonObject();
        JsonArray apartmentsJSON = new JsonArray();
        JsonArray hotelsJSON = new JsonArray();

        for (int a = 0; a < apartments.size(); a++) {
            Accommodation accommodation = new Accommodation();
            accommodation = searchAccommodations.getAccommodationById(apartments.get(a).getId());
            JsonObject apartment = new JsonObject();

            apartment.addProperty("id", accommodation.getId());
            apartment.addProperty("name", accommodation.getName());
            apartment.addProperty("price", accommodation.getPrice());
            apartment.addProperty("cleaningPrice", apartments.get(a).getCleaningPrice());
            apartmentsJSON.add(apartment);
        }

        for (int h = 0; h < hotels.size(); h++) {
            Accommodation accommodation = new Accommodation();
            accommodation = searchAccommodations.getAccommodationById(hotels.get(h).getId());
            JsonObject hotel = new JsonObject();

            hotel.addProperty("id", accommodation.getId());
            hotel.addProperty("name", accommodation.getName());
            hotel.addProperty("price", accommodation.getPrice());
            hotel.addProperty("halfBoardPrice", hotels.get(h).getHalfBoardPrice());
            hotelsJSON.add(hotel);
        }

        result.add("apartments", apartmentsJSON);
        result.add("hotels", hotelsJSON);
        result.addProperty("arrival", String.valueOf(arrival));
        result.addProperty("departure", String.valueOf(departure));
        result.addProperty("days", days);
        result.addProperty("persons", personsNumber);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        out.print(result);
    }
}
