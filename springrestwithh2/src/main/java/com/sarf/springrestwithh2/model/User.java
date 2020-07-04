package com.sarf.springrestwithh2.model;

import lombok.Data;

@Data
public class User {

    private Long id ;
    private String userName = "";
    private String emailId = "";
    private String firstName = "";
    private String lastName = "";
    private String phoneNumber = "";
    private String address = "";
    //Full Name is created by the combination of first name and last name.
    // So, no setter for full name.
    private String fullName ;

}
