package com.example.arads;

public class UserHelperClass {
    String name, username, email, phoneNum, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String email, String phoneNum, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
