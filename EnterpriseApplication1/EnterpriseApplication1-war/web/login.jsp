<%-- 
    Document   : login
    Created on : Jul 13, 2024, 1:28:48 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
<meta name="robots" content="noindex, nofollow">
<title>Login</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

<link rel="stylesheet" href="assets/css/style.css">
</head>
<body class="account-page">
    <form method="post" action="Login">
<div class="main-wrapper">
<div class="account-content">
<div class="login-wrapper">
<div class="login-content">
<div class="login-userset">
<div class="login-userheading">
<h3>Sign In</h3>
<h4>Please login to your account</h4>
</div>
<div class="form-login">
<label>Email</label>
<div class="form-addons">
    <input name="email" required type="email" placeholder="Enter your email address">
<img src="assets/img/icons/mail.svg" alt="img">
</div>
</div>
<div class="form-login">
<label>Password</label>
<div class="pass-group">
<input name="password" required type="password" class="pass-input" placeholder="Enter your password">
<span class="fas toggle-password fa-eye-slash"></span>
</div>
</div>
<div class="form-login">
<input type="submit" class="btn btn-login" value="Sign In">
</div>
<div class="signinform text-center">
<h4>Don’t have an account? <a href="signup.jsp" class="hover-a">Sign Up</a></h4>
</div>
</div>
</div>
<div class="login-img">
<img src="assets/img/zebra.jpg" alt="img">
</div>
</div>
</div>
</div>
</form>

<script src="assets/js/jquery-3.6.0.min.js"></script>

<script src="assets/js/feather.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/js/script.js"></script>
<script>
    const url = new URL(window.location.href);
    const error = url.searchParams.get('e');
    
    if(error == 0){
        Swal.fire("User not found");
    }else if(error == 1){
        Swal.fire("Wrong password");
    }else if(error == 5){
        Swal.fire("Your account has been deactivated following multiple violations of our code of conduct!\n If you\n\
                    think this was an accident please write to us at info@jobhunter.com");
    }
    
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.querySelector('form');
        const emailInput = document.querySelector('input[name="email"]');
        const passwordInput = document.querySelector('input[name="password"]');

        form.addEventListener('submit', function(event) {
            // Prevent form submission if validation fails
            if (!validateEmail(emailInput.value)) {
                event.preventDefault();
                alert('Please enter a valid email address.');
                return;
            }

            if (passwordInput.value.length < 8) {
                event.preventDefault();
                alert('Password must be at least 8 characters long.');
                return;
            }
        });

        // Function to validate email format
        function validateEmail(email) {
            const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return regex.test(email);
        }
    });
</script>

</body>
</html>