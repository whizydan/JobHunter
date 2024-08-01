/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Applications;
import model.ApplicationsFacade;
import model.ShortListFacade;
import model.Shortlist;

/**
 *
 * @author kerberos
 */
public class ShortList extends HttpServlet {
    @EJB
    ShortListFacade shortListFacade;
    
    @EJB
    ApplicationsFacade applicationsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String vacancyId = request.getParameter("id");
      List<Shortlist> shortList = shortListFacade.findAllById(vacancyId);
      
      List<Applications> applications = applicationsFacade.findAllById(vacancyId);
      
      request.setAttribute("applications", applications);
      request.setAttribute("shortlist", shortList);
      request.getRequestDispatcher("shortlist.jsp?id=" + vacancyId).forward(request, response);
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
