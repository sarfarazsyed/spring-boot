package com.sarf.springmvc.model;

import javax.annotation.PostConstruct;

public class User {

    private static Long counter = 1L;
    private Long id = counter++;
    private String userName = "";
    private String emailId = "";
    private String firstName = "";
    private String lastName = "";
    private String phoneNumber = "";
    private String address = "";
    //Full Name is created by the combination of first name and last name.
    // So, no setter for full name.
    private String fullName ;

    public User() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() { return emailId; }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;this.fullName = this.firstName + " " + this.lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;this.fullName = this.firstName + " " + this.lastName;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getFullName() { return this.fullName; }

    public void setFullName(String fullName) {}

    public Long getId() { return this.id; }
}
