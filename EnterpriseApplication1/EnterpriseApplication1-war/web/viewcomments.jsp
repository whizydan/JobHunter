<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-cop.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-4">
    <a id="link" class="btn btn-primary" href="ShortList">Shortlist</a>
    <h2>Comments</h2>
    <ul class="list-group">
        <c:forEach var="comment" items="${comments}">
            <li class="list-group-item">
                <strong>ID:</strong> ${comment.id}<br>
                <strong>User ID:</strong> ${comment.userId}<br>
                <strong>Date:</strong> ${comment.date}<br>
                <strong>Comment:</strong> ${comment.comment}
                <a href="ReportUser?id=${comment.userId}&c=${comment.comment}&d=${comment.id}">
                    <button class="btn btn-primary">Report Comment</button>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
<div hidden class="container card">
    <form method="post" action="ViewComments">
        <input type="text" class="form-control" name="comment" required/>
        <input type="submit" class="btn btn-primary"/>
    </form>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    // Function to get URL parameter by name
    function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    // Get the 'id' parameter from the URL
    let id = getParameterByName('id');

    // If 'id' is found, update the href attribute of the link
    if (id) {
        let linkElement = document.getElementById('link');
        if (linkElement) {
            linkElement.href = "ShortList?id=" + id;
        }
    }
});
</script>

<%@ include file="footer-cop.jsp" %>
