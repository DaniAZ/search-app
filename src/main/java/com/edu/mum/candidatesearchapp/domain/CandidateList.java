package com.edu.mum.candidatesearchapp.domain;


import java.util.Arrays;
import java.util.List;


public class CandidateList {
    private String[] skills;
    private List<Candidate> candidates;

    public CandidateList(){}

    public CandidateList(String[] skills, List<Candidate> candidates) {
        this.skills = skills;
        this.candidates = candidates;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return "CandidateList{" +
                "skills=" + Arrays.toString(skills) +
                ", candidates=" + candidates +
                '}';
    }
}
