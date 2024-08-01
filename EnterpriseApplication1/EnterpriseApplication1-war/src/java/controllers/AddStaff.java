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
public class AddStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        boolean isEnabled = false;
        
        if(status.equals("1")){
            isEnabled = true;
        }
        
        //construct the user
        Users user = new Users();
        user.setAge(Integer.parseInt(age));
        user.setCommentId(0);
        user.setEmail(email);
        user.setEnabled(isEnabled);
        user.setFname(fname);
        user.setGender(gender);
        user.setLname(lname);
        user.setMname("");
        user.setRole(0);
        user.setPassword("@Password2024");
        user.setWarningcount(0);
        user.setWarningmessage("");
        
        usersFacade.create(user);
        
        response.sendRedirect("GetStaff");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
