/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constants;
import model.Users;
import model.UsersFacade;

/**
 *
 * @author kerberos
 */
public class Login extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Users user = usersFacade.findUserByEmail(email);

        if (user == null) {
            // Redirect to login.jsp with error code e=0 (user not found)
            response.sendRedirect("login.jsp?e=0");
        } else {
            if (user.getPassword().equals(password)) {
                if(user.isEnabled()){
                    Cookie name = Constants.setCookie("name", user.getFname() + " " + user.getMname() + " " + user.getLname());
                    Cookie id = Constants.setCookie("userId", String.valueOf(user.getId()));
                    Cookie role = Constants.setCookie("role", String.valueOf(user.getRole()));
                    Cookie emailCookie = Constants.setCookie("email", user.getEmail());
                    
                    response.addCookie(name);
                    response.addCookie(emailCookie);
                    response.addCookie(role);
                    response.addCookie(id);
                    
                    switch (user.getRole()) {
                    case 0:
                        //admin account
                        response.sendRedirect("GetStaff");
                        break;
                    case 1:
                        //corporate customer
                        response.sendRedirect("MyVacancies");
                        break;
                    case 2:
                        //job seeker
                        response.sendRedirect("GetJobs");
                        break;
                    default:
                        //error
                        response.sendRedirect("404.jsp");
                        break;
                }
                }else{
                    response.sendRedirect("login.jsp?e=5");
                }
            } else {
                // Redirect to login.jsp with error code e=1 (wrong password)
                response.sendRedirect("login.jsp?e=1");
            }
        }
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
