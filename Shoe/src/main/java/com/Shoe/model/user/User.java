package com.Shoe.model.user;

import com.Shoe.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name="phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "birthday",nullable = false)
    private Date birthday;
    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name="address",nullable = false)
    private String address;
}
