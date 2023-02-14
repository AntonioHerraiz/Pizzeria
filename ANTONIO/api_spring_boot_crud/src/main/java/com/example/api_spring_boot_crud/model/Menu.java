package com.example.api_spring_boot_crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menus")
public class Menu {
    private int id;
    private String name;
    private String ingredients;
    private String image;
    private Double price;

    public Menu() {

    }

    public Menu(int id, String name, String ingedients, String image, Double price) {
        this.id = id;
        this.name = name;
        this.ingredients = ingedients;
        this.image = image;
        this.price = price;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu [ id = " + this.id + ", name = " + this.name + ", price = " + this.price + " ]";
    }

}
