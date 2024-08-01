<%-- 
    Document   : blank
    Created on : Jul 13, 2024, 2:09:32 PM
    Author     : kerberos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header-job.jsp" %>

<div class="container-fluid p-0">
    <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: fadeInUp;">
            <div class="container">
                <div class="row gy-5 gx-4">
                    <div class="col-lg-8">
                        <div class="d-flex align-items-center mb-5">
                            <img class="flex-shrink-0 img-fluid border rounded" src="${job.logo}" alt="" style="width: 80px; height: 80px;">
                            <div class="text-start ps-4">
                                <h3 class="mb-3">${job.title}</h3>
                                <span class="text-truncate me-3"><i class="fa fa-map-marker-alt text-primary me-2"></i>${job.location}</span>
                                <span class="text-truncate me-3"><i class="far fa-clock text-primary me-2"></i>${job.type}</span>
                                <span class="text-truncate me-0"><i class="far fa-money-bill-alt text-primary me-2"></i>${job.salary}</span>
                            </div>
                        </div>

                        <div class="mb-5">
                            ${job.longDesc}
                        </div>
        
                        <div class="">
                            <h4 class="mb-4">Apply For The Job</h4>
                            <form method="post" action="ApplyJob?id=${job.id}">
                                <div class="row g-3">
                                    <div class="col-12 col-sm-6">
                                        <input name="name" type="text" class="form-control" placeholder="Your Name">
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input name="email" type="email" class="form-control" placeholder="Your Email">
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input name="portfolio" type="text" class="form-control" placeholder="Portfolio Website">
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input name="phone" placeholder="phone" type="text" class="form-control bg-white">
                                    </div>
                                    <div class="col-12">
                                        <textarea name="resume" class="form-control" rows="5" placeholder="Resume"></textarea>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100" type="submit">Apply Now</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
        
                    <div class="col-lg-4">
                        <div class="bg-light rounded p-5 mb-4 wow slideInUp" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: slideInUp;">
                            <h4 class="mb-4">Job Summary</h4>
                            <p><i class="fa fa-angle-right text-primary me-2"></i>Published On: ${job.date}</p>
                            <p><i class="fa fa-angle-right text-primary me-2"></i>Vacancy: ${job.vacancies} Position</p>
                            <p><i class="fa fa-angle-right text-primary me-2"></i>Job Nature: ${job.type}</p>
                            <p><i class="fa fa-angle-right text-primary me-2"></i>Salary: ${job.salary}</p>
                            <p><i class="fa fa-angle-right text-primary me-2"></i>Location: ${job.location}</p>
                            <p class="m-0"><i class="fa fa-angle-right text-primary me-2"></i>Deadline: ${job.deadline}</p>
                        </div>
                        <div class="bg-light rounded p-5 wow slideInUp" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: slideInUp;">
                            <h4 class="mb-4">Company Detail</h4>
                            <p class="m-0">${job.companyInfo}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>

<%@ include file="footer-cop.jsp" %>
