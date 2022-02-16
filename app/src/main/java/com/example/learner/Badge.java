package com.example.learner;

public class Badge {
    String skills, awards, jobs;

    public  Badge(){

    }

    public Badge(String skills, String awards, String jobs) {
        this.skills = skills;
        this.awards = awards;
        this.jobs = jobs;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }
}
