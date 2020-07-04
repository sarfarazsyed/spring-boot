package com.sarf.postgresdbandjpa.model;

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

}
