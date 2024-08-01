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
import model.Comments;
import model.CommentsFacade;

/**
 *
 * @author kerberos
 */
public class ViewComments extends HttpServlet {
    @EJB
    CommentsFacade commentsFacade;
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
            out.println("<title>Servlet ViewComments</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewComments at " + request.getContextPath() + "</h1>");
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
        Long id = Long.parseLong(request.getParameter("id"));
         request.setAttribute("comments", commentsFacade.findById(String.valueOf(id)));
        request.getRequestDispatcher("viewcomments.jsp").forward(request, response);
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
    String commentText = request.getParameter("comment");
    
    // Retrieve userId from cookies
    String userIdStr = null;
    javax.servlet.http.Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (javax.servlet.http.Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                userIdStr = cookie.getValue();
                break;
            }
        }
    }

    // Handle case where userId is not found in cookies
    if (userIdStr == null) {
        // Redirect to login page or handle error
        response.sendRedirect("login.jsp");
        return;
    }

    Long userId = Long.parseLong(userIdStr);

    // Create a new Comments object and set its properties
    Comments comment = new Comments();
    comment.setComment(commentText);
    comment.setUserId(userId);

    // Use the CommentsFacade to persist the new comment
    commentsFacade.create(comment);

    // Redirect to the page where the comment was added
    response.sendRedirect("viewComments");
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
