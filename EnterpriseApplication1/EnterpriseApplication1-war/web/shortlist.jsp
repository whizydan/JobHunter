<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-cop.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid p-4">
    <div class="row">
        <div class="col">
            <h2>Applications</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Vacancy ID</th>
                        <th>User ID</th>
                        <th>Resume</th>
                        <th>Portfolio</th>
                        <th>Email</th>
                        <th>Date</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="application" items="${applications}">
                        <tr>
                            <td>${application.id}</td>
                            <td>${application.vacancyId}</td>
                            <td>${application.userId}</td>
                            <td>${application.resume}</td>
                            <td>${application.portfolio}</td>
                            <td>${application.email}</td>
                            <td>${application.date}</td>
                            <td>${application.fullname}</td>
                            <td>${application.phone}</td>
                            <td class="text-warning"><a class="btn btn-primary" href="OfferJob?id=${application.id}&op=1&v=${application.vacancyId}">Shortlist applicant</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col">
            <h2>Shortlist</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Application ID</th>
                        <th>Offered</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="shortlist" items="${shortlist}">
                        <tr>
                            <td>${shortlist.id}</td>
                            <td>${shortlist.applicationId}</td>
                            <td>${shortlist.offered}</td>
                            <td></td>
                            <td></td>
                            <td class="text-warning"><a hidden class="btn btn-success" href="OfferJob?id=${shortlist.id}&op=0&v=${shortlist.vacancyId}">Offer Job</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="footer-cop.jsp" %>
