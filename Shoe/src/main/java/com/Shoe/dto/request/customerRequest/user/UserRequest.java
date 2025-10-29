package com.Shoe.dto.request.customerRequest.user;

import com.Shoe.enums.Gender;
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

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Date birthDate;
    private Gender gender;
    private String address;

}
