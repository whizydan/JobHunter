<%-- 
    Document   : blank-admin
    Created on : Jul 13, 2024, 2:34:08 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header_admin.jsp" %>
<div class="page-header">
    <div class="page-title">
        <h4>Corporate Customers</h4>
        <h6>Edit corporate member </h6>
    </div>
</div>

<div class="card">
    <form method="post" action="EditCorporate?id=${user.id}" class="needs-validation" novalidate>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>First Name</label>
                        <input value="${user.fname}" name="fname" required type="text" class="form-control" placeholder="Thomas">
                        <div class="invalid-feedback">
                            Please enter a first name.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Last Name</label>
                        <input value="${user.lname}" name="lname" required type="text" class="form-control" placeholder="Thomas">
                        <div class="invalid-feedback">
                            Please enter a last name.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Email</label>
                        <input value="${user.email}" name="email" required type="email" class="form-control" placeholder="xxxx@xxx.xxx">
                        <div class="invalid-feedback">
                            Please enter a valid email address.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Status</label>
                        <select name="status" required class="form-control">
                            <option value="1">Enabled</option>
                            <option value="0">Disabled</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a status.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Age</label>
                        <input value="${user.age}" name="age" required type="number" class="form-control" placeholder="23">
                        <div class="invalid-feedback">
                            Please enter a valid age.
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-12">
                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender" required class="form-control">
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a gender.
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <input type="submit" class="btn btn-submit" value="Save">
                    <a href="#" class="btn btn-cancel">Cancel</a>
                </div>
            </div>
        </div>
    </form>
</div>



<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

<%@ include file="footer-admin.jsp" %>