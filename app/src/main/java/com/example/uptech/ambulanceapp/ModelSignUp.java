package com.example.uptech.ambulanceapp;

public class ModelSignUp {

    public String name;
    public String mail;
    public String phone;
    public String repassword;
    public String Password;

    public ModelSignUp() {
    }

    public ModelSignUp(String name, String mail, String phone, String repassword, String password) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.repassword = repassword;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
