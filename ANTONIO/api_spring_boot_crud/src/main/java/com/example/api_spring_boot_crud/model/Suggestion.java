package com.example.api_spring_boot_crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "suggestions")
public class Suggestion {
    private int id;
    private String name;
    private int user_id;
    private String ingredients;

    public Suggestion() {
    }

    public Suggestion(int id, String name, int user_id, String date, String ingredients) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.ingredients = ingredients;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    // other setters and getters
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Suggestion [ id = " + this.id + ", name = " + this.name + ", Usuario = " + this.user_id + " ]";
    }

}
