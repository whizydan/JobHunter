<%-- 
    Document   : blank-admin
    Created on : Jul 13, 2024, 2:34:08 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header_admin.jsp" %>

<div class="page-header">
<div class="page-title">
<h4>Manage Job Seekers</h4>
</div>
<div class="page-btn">
<a href="addseeker.jsp" class="btn btn-added"><img src="assets/img/icons/plus.svg" alt="img">Add Job Seeker</a>
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
<table class="table  datanew">
<thead>
<tr>
<th>
<label class="checkboxs">
<input type="checkbox" id="select-all">
<span class="checkmarks"></span>
</label>
</th>
<th>Staff Name</th>
<th>StaffID</th>
<th>Age</th>
<th>email</th>
<th>Gender</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<c:forEach var="user" items="${seekers}">
                        <tr>
                            <td>
                                <label class="checkboxs">
                                    <input type="checkbox">
                                    <span class="checkmarks"></span>
                                </label>
                            </td>
                            <td>
                                ${user.fname} ${user.lname}
                            </td>
                            <td>${user.id}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>${user.gender}</td>
                            <td>
                                <a class="me-3" href="EditSeeker?id=${user.id}">
                                    <img src="assets/img/icons/edit.svg" alt="img">
                                </a>
                                <a class="me-3" href="DeleteSeeker?id=${user.id}">
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

<%@ include file="footer-admin.jsp" %>