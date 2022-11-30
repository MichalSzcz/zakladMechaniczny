package com.example.zakladmechanicznyspringboot.model;

import lombok.*;

import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLogging {
    private String email;
    private String password;
    private String type;
}
