<%-- 
    Document   : blank-admin
    Created on : Jul 13, 2024, 2:34:08 PM
    Author     : kerberos
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header-cop.jsp" %>

<div class="page-header">
    <div class="page-title">
        <h4>Manage Vacancies</h4>
    </div>
    <div class="page-btn">
        <a href="addvacancy.jsp" class="btn btn-added"><img src="assets/img/icons/plus.svg" alt="img">Add Job Vacancy</a>
    </div>
</div>

<div class="card">
    <div class="card-body">
        <div class="table-top">
            <div class="search-set">
                <div hidden class="search-path">
                    <a class="btn btn-filter" id="filter_search">
                        <img src="assets/img/icons/filter.svg" alt="img">
                        <span><img src="assets/img/icons/closes.svg" alt="img"></span>
                    </a>
                </div>
                <div class="search-input">
                    <a class="btn btn-searchset"><img src="assets/img/icons/search-white.svg" alt="img"></a>
                </div>
            </div>
            <div class="wordset">
                <ul>
                    <li>
                        <a data-bs-toggle="tooltip" onClick="window.print();" data-bs-placement="top" title="print"><img src="assets/img/icons/printer.svg" alt="img"></a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table datanew">
                <thead>
                    <tr>
                        <th>
                            <label class="checkboxs">
                                <input type="checkbox" id="select-all">
                                <span class="checkmarks"></span>
                            </label>
                        </th>
                        <th>Job Title</th>
                        <th>Date posted</th>
                        <th>Salary Range</th>
                        <th>Type</th>
                        <th>Category</th>
                        <th>Deadline</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vacancy" items="${vacancies}">
                        <tr>
                            <td>
                                <label class="checkboxs">
                                    <input type="checkbox">
                                    <span class="checkmarks"></span>
                                </label>
                            </td>
                            <td>
                                <a href="javascript:void(0);">${vacancy.title}</a>
                            </td>
                            <td>${vacancy.date}</td>
                            <td>${vacancy.salary}</td>
                            <td>${vacancy.type}</td>
                            <td>${vacancy.category}</td>
                            <td>${vacancy.deadline}</td>
                            <td>
                                <a class="me-3" href="ViewComments?id=${vacancy.id}">
                                    <img src="assets/img/icons/eye.svg" alt="img">
                                </a>
                                <a class="me-3" href="EditVacancy?id=${vacancy.id}">
                                    <img src="assets/img/icons/edit.svg" alt="img">
                                </a>
                                <a class="me-3" href="DeleteVacancy?id=${vacancy.id}">
                                    <img src="assets/img/icons/delete.svg" alt="img">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="footer-cop.jsp" %>
