<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header_admin.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-4">
    <div class="row">
        <!-- Left Column: User Feedback Data -->
        <div class="col-md-6">
            <h4>User Feedback</h4>
            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <td>${feedback.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${feedback.fname} ${feedback.lname} ${feedback.mname}</td>
                </tr>
                <tr>
                    <th>Feedback</th>
                    <td>${feedback.feeddback}</td>
                </tr>
                <tr>
                    <th>Age</th>
                    <td>${feedback.age}</td>
                </tr>
                <tr>
                    <th>Date</th>
                    <td>${feedback.date}</td>
                </tr>
                <tr>
                    <th>Issue Type</th>
                    <td>${feedback.issueType}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${feedback.email}</td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td>${feedback.gender}</td>
                </tr>
                <tr>
                    <th>Warning Count</th>
                    <td>${feedback.warningCount}</td>
                </tr>
            </table>
        </div>
        
        <!-- Right Column: List of Comments -->
        <div class="col-md-6">
            <h4>Comments</h4>
            <ul class="list-group">
                <c:forEach var="comment" items="${comments}">
                    <li class="list-group-item">
                        <p>${comment.comment}</p>
                        <small>${comment.date}</small>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="footer-admin.jsp" %>
