package com.example.zakladmechanicznyspringboot.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    public User(User user) {
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
    }

}