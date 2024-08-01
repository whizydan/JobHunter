/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ApplicationsFacade;
import model.ShortListFacade;
import model.Shortlist;

/**
 *
 * @author kerberos
 */
public class Check extends HttpServlet {
@EJB
ApplicationsFacade apps;

@EJB
ShortListFacade shortlist;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationId = request.getParameter("id");
        
        Shortlist shorty = shortlist.findByApplication(applicationId);
        response.setContentType("application/json");
        if(shorty != null){
            JsonObject jsonResponse = Json.createObjectBuilder()
                .add("id", shorty.getId())
                .add("applicationId", shorty.getApplicationId())
                .add("offered", true)
                .add("offerAccepted", shorty.isOfferAccepted())
                .add("rejectionReason", shorty.getRejectionReason())
                .build();
        
        response.getWriter().write(jsonResponse.toString());
        }else{
            JsonObject jsonResponse = Json.createObjectBuilder()
                .add("offered", false)
                .build();
        
        response.getWriter().write(jsonResponse.toString());
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
