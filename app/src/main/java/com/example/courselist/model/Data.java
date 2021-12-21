package com.example.courselist.model;

public class Data {

    private String nim, name, email, phone;

    public Data() {

    }

    public Data (String nim, String name, String email, String phone) {
        this.nim = nim;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
