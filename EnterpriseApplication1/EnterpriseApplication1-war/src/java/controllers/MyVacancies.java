/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Vacancies;
import model.VacanciesFacade;

/**
 *
 * @author kerberos
 */
public class MyVacancies extends HttpServlet {
    @EJB
    VacanciesFacade vacanciesFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve userId from cookies
        String userId = null;
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }

        // Handle case where userId is not found in cookies
        if (userId == null) {
            // Redirect to login page or handle error
            response.sendRedirect("login.jsp");
            return;
        }

        // Use userId to find vacancies
        List<Vacancies> vacancies = vacanciesFacade.findAllMine(userId);
        request.setAttribute("vacancies", vacancies);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String title = request.getParameter("title");
        String salary = request.getParameter("salary");
        String shortDesc = request.getParameter("shortDesc");
        String type = request.getParameter("type");
        String category = request.getParameter("category");
        String email = request.getParameter("email");
        int vacancies = Integer.parseInt(request.getParameter("vacancies"));
        String duration = request.getParameter("duration");
        String deadlineStr = request.getParameter("deadline");
        String location = request.getParameter("location");
        String logo = request.getParameter("logo");
        String companyDesc = request.getParameter("companyDesc");
        String longDesc = request.getParameter("longDesc");

        // Parse date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date deadline = null;
        try {
            deadline = sdf.parse(deadlineStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Retrieve userId from cookies
        String userId = null;
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }

        // Handle case where userId is not found in cookies
        if (userId == null) {
            // Redirect to login page or handle error
            response.sendRedirect("login.jsp");
            return;
        }

        // Create a new Vacancies object
        Vacancies vacancy = new Vacancies();
        vacancy.setCorporateUserId(Long.parseLong(userId)); // Assuming userId is a long
        vacancy.setTitle(title);
        vacancy.setSalary(salary);
        vacancy.setShortDesc(shortDesc);
        vacancy.setType(type);
        vacancy.setCategory(category);
        vacancy.setEmail(email);
        vacancy.setVacancies(vacancies);
        vacancy.setDuration(duration);
        vacancy.setDeadline(deadline);
        vacancy.setLocation(location);
        vacancy.setLogo(logo);
        vacancy.setCompanyInfo(companyDesc);
        vacancy.setLongDesc(longDesc);
        vacancy.setDate(new Date()); // Set current date

        // Use the VacanciesFacade to persist the new vacancy
        vacanciesFacade.create(vacancy);

        // Redirect to the home page or another page after successful creation
        response.sendRedirect("MyVacancies");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
