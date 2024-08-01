<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header_admin.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-4">
    <h2>Reports</h2>
    <div class="row">
        <c:forEach var="report" items="${reports}">
            <div class="col-md-6 mb-4 p-3">
                <div class="card">
                    <div class="card-header">
                        <h5>${report.title}</h5>
                    </div>
                    <div class="card-body">
                        <p>${report.description}</p>
                        <pre>${report.data}</pre>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="footer-admin.jsp" %>
