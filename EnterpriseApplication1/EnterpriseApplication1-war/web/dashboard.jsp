<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-job.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid p-4">
    <ul>
        <c:forEach var="job" items="${jobs}">
            <li>
                <div class="card job-item p-4 mb-4">
                    <div class="row g-4">
                        <div class="col-sm-12 col-md-8 d-flex align-items-center">
                            <img class="flex-shrink-0 img-fluid border rounded" src="${job.logo}" alt="Company Logo" style="width: 80px; height: 80px;">
                            <div class="text-start ps-4">
                                <h5 class="mb-3">${job.title}</h5>
                                <span class="text-truncate me-3"><i class="fa fa-map-marker-alt text-primary me-2"></i>${job.location}</span>
                                <span class="text-truncate me-3"><i class="far fa-clock text-primary me-2"></i>${job.type}</span>
                                <span class="text-truncate me-0"><i class="far fa-money-bill-alt text-primary me-2"></i>${job.salary}</span>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-4 d-flex flex-column align-items-start align-items-md-end justify-content-center">
                            <div class="d-flex mb-3">
                                <a class="btn btn-primary" href="GetJob?id=${job.id}">Apply Now</a>
                            </div>
                            <small class="text-truncate"><i class="far fa-calendar-alt text-primary me-2"></i>Deadline: ${job.deadline}</small>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>

<%@ include file="footer-job.jsp" %>
