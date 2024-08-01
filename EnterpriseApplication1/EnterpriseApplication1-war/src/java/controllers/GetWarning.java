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
import model.CommentsFacade;
import model.Users;
import model.UsersFacade;

/**
 *
 * @author kerberos
 */
public class GetWarning extends HttpServlet {
    
    @EJB
    UsersFacade usersFacade;
    
    @EJB
    CommentsFacade comments;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        try{
            if(userIdStr != null){
                Users user = usersFacade.find(userIdStr);
                request.setAttribute("warning", user.getWarningmessage());
                request.setAttribute("comment", comments.find(String.valueOf(user.getCommnetId())));
            }else{
                request.setAttribute("warning", "An error ocurred while fetching your account details!");
            }
        }catch(Exception ex){
            request.setAttribute("warning", ex.getLocalizedMessage());
        }
        request.getRequestDispatcher("warning.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String getCookieValue(HttpServletRequest request, String name) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
            return null;
        }
}
