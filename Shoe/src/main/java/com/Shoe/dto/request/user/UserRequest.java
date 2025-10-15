package com.Shoe.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Date birthDate;
}
