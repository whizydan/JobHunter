/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kerberos
 */
public class UserApps {
    private String title;
    private String logo;
    private String deadline;
    private String applicationDate;
    private String salary;
    private String duration;
    private long id;

    // Getters
    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getSalary() {
        return salary;
    }

    public String getDuration() {
        return duration;
    }

    public long getId() {
        return id;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setId(long id) {
        this.id = id;
    }
}
