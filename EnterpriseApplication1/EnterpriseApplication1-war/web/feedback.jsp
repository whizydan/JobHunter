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
<h4>Feedback</h4>
</div>
<div hidden class="page-btn">
<a class="btn btn-added"><img src="assets/img/icons/plus.svg" alt="img">Feedback</a>
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
<th>Corporate Customer Name</th>
<th>Job Seeker Name</th>
<th>Age</th>
<th>Comment</th>
<th>Date</th>
<th>Action</th>
</tr>
</thead>
<tbody>

<c:forEach var="feedback" items="${feedbacks}">
    <tr>
<td>
<label class="checkboxs">
<input type="checkbox">
<span class="checkmarks"></span>
</label>
</td>
<td class="productimgname">
<a href="javascript:void(0);">${feedback.id}</a>
</td>
<td>${feedback.fname} ${feedback.mname} ${feeedback.lname}</td>
<td>${feedback.age}</td>
<td>${feedback.feedback} </td>
<td><a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="1165797e7c7062517469707c617d743f727e7c">[email&#160;protected]</a></td>
<td hidden>
<a class="me-3" href="GetFeedbackInfo?id=${feedback.id}">
<img src="assets/img/icons/eye.svg" alt="img">
</a>
<a hidden class="me-3" href="DeleteF?id=1">
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