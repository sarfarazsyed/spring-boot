package com.sarf.postgresdbandjpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    private String userName = "";
    private String emailId = "";
    private String firstName = "";
    private String lastName = "";
    private String phoneNumber = "";
    private String address = "";

}
