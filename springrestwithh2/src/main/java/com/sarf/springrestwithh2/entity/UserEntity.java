package com.sarf.springrestwithh2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Getter@Setter
    private Long id;
    @Getter@Setter
    private String userName = "";
    @Getter@Setter
    private String emailId = "";
    @Getter
    private String firstName = "";
    @Getter
    private String lastName = "";
    @Getter@Setter
    private String phoneNumber = "";
    @Getter@Setter
    private String address = "";
    @Transient
    @Getter
    private String fullName ;
    public void setFirstName(String firstName) {
        this.firstName = firstName;this.fullName = this.firstName + " " + this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;this.fullName = this.firstName + " " + this.lastName;
    }
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
