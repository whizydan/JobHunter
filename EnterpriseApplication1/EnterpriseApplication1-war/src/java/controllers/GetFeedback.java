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
import model.Feedback;
import model.FeedbackFacade;
import model.UsersFacade;
import model.UsersFeedback;

/**
 *
 * @author kerberos
 */
public class GetFeedback extends HttpServlet {
    
    @EJB
    FeedbackFacade feedbackFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UsersFeedback> feedback = feedbackFacade.findAll();
        
        request.setAttribute("feedbacks", feedback);
        request.getRequestDispatcher("feedback.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
