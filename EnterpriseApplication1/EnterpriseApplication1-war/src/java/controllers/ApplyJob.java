/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Applications;
import model.ApplicationsFacade;
import model.Vacancies;
import model.VacanciesFacade;

/**
 *
 * @author kerberos
 */
public class ApplyJob extends HttpServlet {

    @EJB
    ApplicationsFacade applications;
    
    @EJB
    VacanciesFacade vacancies;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String portfolio = request.getParameter("portfolio");
    String resume = request.getParameter("resume");
    String id = request.getParameter("id");
    
    Vacancies vacancy = vacancies.find(Long.parseLong(id));

    // Retrieve userId from cookies
    Long userId = null;
    javax.servlet.http.Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (javax.servlet.http.Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                userId = Long.parseLong(cookie.getValue());
                break;
            }
        }
    }

    // Check if userId is found
    if (userId == null) {
        // Redirect to login page or handle error
        response.sendRedirect("login.jsp");
        return;
    }

    Applications application = new Applications();
    application.setDate(new Date());
    application.setEmail(email);
    application.setFullname(name);
    application.setPhone(phone);
    application.setPortfolio(portfolio);
    application.setResume(resume);
    application.setUserId(userId);
    application.setVacancyId(Long.parseLong(id));

    applications.create(application);
    response.sendRedirect("MyApps");
}


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
