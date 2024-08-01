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
<h4>Manage Corporate Staff</h4>
</div>
<div class="page-btn">
<a href="addcorporate.jsp" class="btn btn-added"><img src="assets/img/icons/plus.svg" alt="img">Add Corporate Customer</a>
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
<th>Enabled</th>
<th>email</th>
<th>Gender</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<c:forEach var="staff" items="${corporate}">
    <tr>
<td>
<label class="checkboxs">
<input type="checkbox">
<span class="checkmarks"></span>
</label>
</td>
<td>
<a href="javascript:void(0);">${staff.fname} ${staff.lname}</a>
</td>
<td>${staff.id}</td>
<td>${staff.age}</td>
<td>${staff.enabled} </td>
<td>${staff.email}</td>
<td>${staff.gender}</td>
<td>
<a class="me-3" href="EditCorporate?id=${staff.id}">
<img src="assets/img/icons/edit.svg" alt="img">
</a>
<a class="me-3" href="DeleteCorporate?id=${staff.id}">
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