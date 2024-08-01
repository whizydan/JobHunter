<%-- 
    Document   : logout
    Created on : Jul 13, 2024, 4:13:45 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
        <script type="text/javascript">
            function clearCookies() {
                var cookies = document.cookie.split("; ");
                for (var i = 0; i < cookies.length; i++) {
                    var cookie = cookies[i];
                    var eqPos = cookie.indexOf("=");
                    var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                    document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
                }
                window.location.href = 'index.jsp';
            }
            window.onload = clearCookies;
        </script>
    </head>
    <body>
        <h1>Logging out...</h1>
    </body>
</html>
