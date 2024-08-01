<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-job.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
<div class="page-title">
<h4>My Applications</h4>
</div>
<div class="page-btn">
<a href="GetJobs" class="btn btn-added"><img src="assets/img/icons/plus.svg" alt="img">Find Work</a>
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
<th>Application Date</th>
<th>Deadline</th>
<th>Salary</th>
<th>Duration</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<c:forEach var="application" items="${applications}">
<tr>
<td>
<label class="checkboxs">
<input type="checkbox">
<span class="checkmarks"></span>
</label>
</td>
<td>
<a href="View?id=${application.id}">${application.title}</a>
</td>
<td>${application.applicationDate}</td>
<td>${application.deadline}</td>
<td>${application.salary}</td>
<td>${application.duration}</td>
<td>
<a hidden class="me-3" href="View?id=${application.id}">
<img src="assets/img/icons/edit.svg" alt="img">
</a>
<a class="me-3" href="DeleteApp?id=${application.id}">
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

<%@ include file="footer-job.jsp" %>
