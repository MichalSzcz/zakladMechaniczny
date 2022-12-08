package com.example.zakladmechanicznyspringboot.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Zaklad {
    private String name;

    Zaklad(Zaklad zaklad) {
        this.name = zaklad.getName();
    }



}
