<%-- 
    Document   : blank
    Created on : Jul 13, 2024, 2:09:32 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-job.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid p-0">
    <%
        if(request.getAttribute("warning") == null){
            %>
            <div class="card-body p-3 mb-3 alert-success" role="alert">No Warnings about your account yet.</div>
            <%
        }else{
        %>
        <div class="card">
        <div class="card-body p-3 mb-3 alert-danger" role="alert"><%= request.getAttribute("warning") %></div>
        <div class="card-footer">
            <c:forEach var="data" items="${comment}">
                <p>Comment: ${data.comment}</p>
                <p>Date: ${data.date}</p>
            </c:forEach>
        </div>
        </div>
        <%
        }
    %>
</div>

<%@ include file="footer-job.jsp" %>
