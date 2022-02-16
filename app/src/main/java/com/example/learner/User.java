package com.example.learner;

public class User {
    private String fullname, email, college, department, year;

    public User(){

    }

    public User(String fullname, String email, String college, String department, String year) {
        this.fullname = fullname;
        this.email = email;
        this.college = college;
        this.department = department;
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", college='" + college + '\'' +
                ", department='" + department + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
