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
import model.Comments;
import model.CommentsFacade;
import model.Feedback;
import model.FeedbackFacade;
import model.UsersFacade;
import model.Vacancies;
import model.VacanciesFacade;

/**
 *
 * @author kerberos
 */
public class ReportUser extends HttpServlet {

    @EJB
    UsersFacade usersFacade;
    
    @EJB
    FeedbackFacade feedbackFacade;
    
    @EJB
    CommentsFacade commentsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    Long seekerId = Long.parseLong(request.getParameter("id"));
    String comment = request.getParameter("c");
    String commentId = request.getParameter("d");

    // Retrieve corporateUserId from cookies
    String corporateUserIdStr = null;
    javax.servlet.http.Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (javax.servlet.http.Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                corporateUserIdStr = cookie.getValue();
                break;
            }
        }
    }

    // Handle case where corporateUserId is not found in cookies
    if (corporateUserIdStr == null) {
        // Redirect to login page or handle error
        response.sendRedirect("login.jsp");
        return;
    }

    Long corporateUserId = Long.parseLong(corporateUserIdStr);

    // Update the warning count for the user
    usersFacade.updateWarningCount(String.valueOf(seekerId), Integer.parseInt(commentId));
    List<Comments> vacancy = commentsFacade.find(commentId);
    

    // Create a new Feedback object and set its properties
    Feedback feedback = new Feedback();
    feedback.setFeedback("User " + seekerId + " commented " + comment + " breaching code of conduct");
    feedback.setCorporateUserId(corporateUserId);
    feedback.setDate(new java.util.Date()); // Set the current date
    feedback.setIssueType("complaint");
    feedback.setJobSeekerId(seekerId);
    feedback.setResolved(false);

    // Use the FeedbackFacade to persist the new feedback
    feedbackFacade.create(feedback);

    // Redirect to the ViewComments page
    response.sendRedirect("ViewComments?id=" + vacancy.get(0).getId());
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
