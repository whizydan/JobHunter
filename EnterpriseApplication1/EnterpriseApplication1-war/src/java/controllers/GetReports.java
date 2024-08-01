package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Applications;
import model.ApplicationsFacade;
import model.Feedback;
import model.FeedbackFacade;
import model.Comments;
import model.CommentsFacade;
import model.Report;
import model.Users;
import model.UsersFacade;
import model.UsersFeedback;
import model.Vacancies;
import model.VacanciesFacade;


public class GetReports extends HttpServlet {

    @EJB
    private FeedbackFacade feedbackFacade;

    @EJB
    private CommentsFacade commentsFacade;
    
    @EJB
    UsersFacade usersFacade;
    
    @EJB
    VacanciesFacade vacanciesFacade;
    
    @EJB
    ApplicationsFacade applicationsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Report> reports = generateReports();
        request.setAttribute("reports", reports);
        request.getRequestDispatcher("reports.jsp").forward(request, response);
    }

    private List<Report> generateReports() {
        List<Report> reports = new ArrayList<>();
        
        // Fetch data from EJBs and generate reports
        reports.add(generateFeedbackReport());
        reports.add(generateCommentsReport());
        reports.add(generateUsersReport());
        reports.add(generateVacanciesReport());
        reports.add(generateFinalReport());
        
        return reports;
    }

    private Report generateFeedbackReport() {
        List<UsersFeedback> feedbackList = feedbackFacade.findAll();
        int totalFeedback = feedbackList.size();
        long resolvedCount = 0;
        long unresolvedCount = totalFeedback - resolvedCount;

        String data = String.format("Total Feedback: %d\nResolved: %d\nUnresolved: %d", totalFeedback, resolvedCount, unresolvedCount);
        return new Report("Feedback Analysis", "Analysis of feedback data.", data);
    }

    private Report generateCommentsReport() {
        List<Comments> commentsList = commentsFacade.findAll();
        int totalComments = commentsList.size();
        long positiveComments = commentsList.stream().filter(comment -> comment.getComment().contains("good")).count();
        long negativeComments = commentsList.stream().filter(comment -> comment.getComment().contains("bad")).count();

        String data = String.format("Total Comments: %d\nPositive: %d\nNegative: %d", totalComments, positiveComments, negativeComments);
        return new Report("Comments Analysis", "Analysis of comments data.", data);
    }

    // Placeholder methods for other reports
    private Report generateUsersReport() {
        List<Users> users = usersFacade.findAll();
        int totalUsers = users.size();
        long admins = users.stream().filter(user -> user.getRole() == 0).count();
        long jobSeekers = users.stream().filter(user -> user.getRole() == 2).count();
        long corporate = users.stream().filter(user -> user.getRole() == 1).count();
        String data = String.format("Total Users: %d\nAdmins: %d\nJobSeekers: %d\nCorporate Customers: %d", totalUsers , admins, jobSeekers, corporate);
        return new Report("Users Report", "Analysis of users.", data);
    }

    private Report generateVacanciesReport() {
        List<Vacancies> vacancies = vacanciesFacade.findAll();
        int totalVacancies = vacancies.size();
        long fullTime = vacancies.stream().filter(vacancy -> vacancy.getDuration().equals("Full time")).count();
        long partTime = vacancies.stream().filter(vacancy -> vacancy.getDuration().equals("Part time")).count();
        long vacanciesTotal = vacancies.stream().filter(user -> user.getVacancies() > 1).count();
        String data = String.format("Total Vacancies: %d\nFull time Jobs: %d\nPart time jobs: %d\nVacancies with multiple openings: %d", totalVacancies ,fullTime, partTime, vacanciesTotal);
        return new Report("Vacancies Report", "Analysis of Vacancies.", data);
    }

    private Report generateFinalReport() {
        List<Applications> vacancies = applicationsFacade.findAll();
        int totalVacancies = vacancies.size();
        long fullTime = vacancies.stream().filter(vacancy -> vacancy.getResume() == null).count();
        long partTime = vacancies.stream().filter(vacancy -> vacancy.getPortfolio() == null).count();
        String data = String.format("Total Applications: %d\nApplications without a resume: %d\nApplications without portfolio: %d", totalVacancies ,fullTime, partTime);
        return new Report("Applications Report", "Analysis of Applications.", data);
    }

    @Override
    public String getServletInfo() {
        return "Generates and displays reports";
    }
}
