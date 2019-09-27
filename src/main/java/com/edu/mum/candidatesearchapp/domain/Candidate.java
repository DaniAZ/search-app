package com.edu.mum.candidatesearchapp.domain;



import java.util.Arrays;

public class Candidate implements Comparable<Candidate>{
    private String name;
    private String project;
    private int year;
    private String[] skills;

    public Candidate(){}
    public Candidate(String name, String project, int year, String[] skills) {
        this.name = name;
        this.project = project;
        this.year = year;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", project='" + project + '\'' +
                ", year=" + year +
                ", skills=" + Arrays.toString(skills) +
                '}';
    }

    @Override
    public int compareTo(Candidate o) {
        return o.getYear() - this.year;
    }
}
