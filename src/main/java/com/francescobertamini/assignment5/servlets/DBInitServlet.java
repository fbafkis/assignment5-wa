package com.francescobertamini.assignment5.servlets;

import com.francescobertamini.assignment5.ejb.DBInit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dbInitServlet", value = "dbInit")
public class DBInitServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Context ctx = null;
        DBInit dbInit=null;
        try {
            ctx = new InitialContext();
            String name="java:module/DBInitBean!com.francescobertamini.assignment5.ejb.DBInit";
            dbInit= (DBInit) ctx.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            dbInit.emptyDB();
            dbInit.insertAccommodations();
            response.sendRedirect("home");
        } catch (Exception e) {
            response.sendError(500);
            response.sendRedirect("error");
        }
    }
}
