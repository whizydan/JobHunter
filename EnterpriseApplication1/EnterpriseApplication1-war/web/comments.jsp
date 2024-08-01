<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-job.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-4">
    <h2>Comments</h2>
    <div hidden id="hire" class="bg-success alert" role="alert">You have been Successfully hired for this job</div>
    <ul class="list-group">
        <c:forEach var="comment" items="${comments}">
            <li class="list-group-item">
                <strong>ID:</strong> ${comment.id}<br>
                <strong>User ID:</strong> ${comment.userId}<br>
                <strong>Date:</strong> ${comment.date}<br>
                <strong>Comment:</strong> ${comment.comment}
            </li>
        </c:forEach>
    </ul>
</div>
<div class="container card">
    <form method="post" action="View">
        <input id="id" type="text" hidden name="id">
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
    const name = document.getElementById("id");
    
    name.value = id;

   fetch('Check?id=' + id)
                .then(response => response.json())
                .then(data => {
                    console.log('Shortlist Data:', data);
                    // Process the data as needed
                    if(data.offered){
                        Swal.fire("You have been shortlisted for this job.\nAwait further communication in the email you provided");
                    }
                })
                .catch(error => console.error('Error:', error)); 
});
</script>

<%@ include file="footer-job.jsp" %>
