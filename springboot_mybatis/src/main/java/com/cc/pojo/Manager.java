package com.cc.pojo;

import org.springframework.stereotype.Component;

@Component
public class Manager {
    private int mid;
    private int tid;
    private String jurisdiction;
    private String institute;

    public Manager() {
    }

    public Manager(int mid, int tid, String jurisdiction, String institute) {
        this.mid = mid;
        this.tid = tid;
        this.jurisdiction = jurisdiction;
        this.institute = institute;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mid=" + mid +
                ", tid=" + tid +
                ", jurisdiction='" + jurisdiction + '\'' +
                ", institute='" + institute + '\'' +
                '}';
    }
}
