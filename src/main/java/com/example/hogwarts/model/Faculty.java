package com.example.hogwarts.model;

@com.example.hogwarts.model.Entity
public class Faculty {

    @com.example.hogwarts.model.Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;
}