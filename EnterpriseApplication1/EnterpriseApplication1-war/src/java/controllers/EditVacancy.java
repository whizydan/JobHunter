/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EditVacancy extends HttpServlet {
@EJB
    VacanciesFacade vacanciesFacade;
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
            out.println("<title>Servlet EditVacancy</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditVacancy at " + request.getContextPath() + "</h1>");
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
        Vacancies vacancy = vacanciesFacade.find(id);
        
        request.setAttribute("vacancy", vacancy);
        request.getRequestDispatcher("editvacancy.jsp?id=" + id).forward(request, response);
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
        String id = request.getParameter("id");

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
        vacancy.setId(Long.parseLong(id));

        // Use the VacanciesFacade to persist the new vacancy
        vacanciesFacade.update(vacancy);

        // Redirect to the home page or another page after successful creation
        response.sendRedirect("MyVacancies");
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
