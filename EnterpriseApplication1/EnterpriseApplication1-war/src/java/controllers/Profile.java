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
import model.Users;
import model.UsersFacade;

/**
 *
 * @author kerberos
 */
public class Profile extends HttpServlet {
    
    @EJB
    UsersFacade users;
    
    long userid;
    String role = null;
    Users user;
    
    private void getUserData(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("role".equals(cookie.getName())) {
                    role = cookie.getValue();
                }
                if ("userId".equals(cookie.getName())) {
                    userid = Long.parseLong(cookie.getValue());
                    break;
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getUserData(request);
        user = users.find(String.valueOf(userid));
        
        String headerFile = "header-job.jsp";
        if (user != null) {
            switch (user.getRole()) {
                case 0:
                    headerFile = "header_admin.jsp";
                    break;
                case 1:
                    headerFile = "header-cop.jsp";
                    break;
                case 2:
                    headerFile = "header-job.jsp";
                    break;
                default:
                    headerFile = "nn";
                    break;
            }
        }
        
        request.setAttribute("user", user);
        request.setAttribute("header", headerFile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        getUserData(request);
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        
        user.setAge(Integer.parseInt(age));
        user.setEmail(email);
        user.setFname(fname);
        user.setGender(gender);
        user.setLname(lname);
        user.setId(userid);
        
        users.update(user);
        
        response.sendRedirect("Profile");
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
