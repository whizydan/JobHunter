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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Users;
import model.UsersFacade;

/**
 *
 * @author kerberos
 */
public class EditSeeker extends HttpServlet {
@EJB
    private UsersFacade usersFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditSeeker</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSeeker at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
       String staffId = request.getParameter("id");
        Users user = usersFacade.find(staffId);
        
        request.setAttribute("user", user);
        request.getRequestDispatcher("editseeker.jsp?id=" + staffId).forward(request, response);
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
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String userId = request.getParameter("id");
        boolean isEnabled = false;
        
        if(status.equals("1")){
            isEnabled = true;
        }
        
        Users user = new Users();
        user.setAge(Integer.parseInt(age));
        user.setCommentId(0);
        user.setEmail(email);
        user.setId(Long.parseLong(userId));
        user.setEnabled(isEnabled);
        user.setFname(fname);
        user.setGender(gender);
        user.setLname(lname);
        user.setMname("");
        user.setRole(0);
        user.setPassword("@Password2024");
        user.setWarningcount(0);
        user.setWarningmessage("");
        
        usersFacade.update(user);
        
        response.sendRedirect("GetSeekers");
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