package com.example.courselist.model;

public class Data2 {

    private String subject_code, subject_name, subject_credit;

    public Data2() {

    }

    public Data2(String subject_code, String subject_name, String subject_credit) {
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.subject_credit = subject_credit;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_credit() {
        return subject_credit;
    }

    public void setSubject_credit(String subject_credit) {
        this.subject_credit = subject_credit;
    }
}
