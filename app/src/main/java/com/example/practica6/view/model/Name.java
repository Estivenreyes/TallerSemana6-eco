package com.example.practica6.view.model;

public class Name {

    private String type = "Name";
    private String name;

    public Name(String name) {

        this.name=name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}