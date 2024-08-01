<%-- 
    Document   : blank-admin
    Created on : Jul 13, 2024, 2:34:08 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header-cop.jsp" %>

<div class="page-header">
    <div class="page-title">
        <h4>Vacancy Management</h4>
        <h6>Add vacancy</h6>
    </div>
</div>

<form method="post" action="EditVacancy" class="needs-validation" novalidate>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div hidden class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Title</label>
                        <input value="${vacancy.id}" name="id" required type="text" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a job title.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Title</label>
                        <input value="${vacancy.title}" name="title" required type="text" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a job title.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Salary</label>
                        <input value="${vacancy.salary}" name="salary" required type="text" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a salary.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Short Description</label>
                        <input value="${vacancy.shortDesc}" type="text" required name="shortDesc" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a short description.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Type</label>
                        <select required name="type" class="form-select">
                            <option value="">Choose...</option>
                            <option>Full time</option>
                            <option>Partial</option>
                            <option>Remote</option>
                            <option>Partial</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a job type.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Category</label>
                        <select required name="category" class="form-select">
                            <option value="">Choose...</option>
                            <option>Engineering</option>
                            <option>Medicine</option>
                            <option>Computing</option>
                            <option>Economics</option>
                            <option>Electronics & Electrical</option>
                            <option>Environment</option>
                            <option>Education</option>
                            <option>Farming & Agriculture</option>
                            <option>Marketing</option>
                            <option>Customer Service</option>
                            <option>Human Resource</option>
                            <option>Project Management</option>
                            <option>Business Development</option>
                            <option>Sales and Communication</option>
                            <option>Design</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a category.
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-12">
                    <div class="form-group">
                        <label>Company Email</label>
                        <input value="${vacancy.email}" type="email" name="email" required class="form-control">
                        <div class="invalid-feedback">
                            Please provide a valid email.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Vacancies</label>
                        <input value="${vacancy.vacancies}" type="number" required name="vacancies" class="form-control">
                        <div class="invalid-feedback">
                            Please provide the number of vacancies.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Duration</label>
                        <input value="${vacancy.duration}" type="text" required name="duration" class="form-control">
                        <div class="invalid-feedback">
                            Please provide the duration.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Deadline</label>
                        <input vlue="${vacancy.deadline}" type="date" required name="deadline" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a deadline.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Location</label>
                        <input value="${vacancy.location}" type="text" required name="location" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a location.
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 col-12">
                    <div class="form-group">
                        <label>Logo Url</label>
                        <input value="${vacancy.logo}" type="url" required name="logo" class="form-control">
                        <div class="invalid-feedback">
                            Please provide a logo URL.
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="form-group">
                        <label>Company Description</label>
                        <textarea required name="companyDesc" class="form-control">${vacancy.companyInfo}</textarea>
                        <div class="invalid-feedback">
                            Please provide a company description.
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="form-group">
                        <label>Description</label>
                        <textarea required name="longDesc" class="form-control">${vacancy.longDesc}</textarea>
                        <div class="invalid-feedback">
                            Please provide a job description.
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <input type="submit" class="btn btn-submit" value="Save">
                    <a href="#" class="btn btn-cancel">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</form>

<%@ include file="footer-cop.jsp" %>

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
