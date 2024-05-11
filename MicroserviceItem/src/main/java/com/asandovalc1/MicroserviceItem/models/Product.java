package com.asandovalc1.MicroserviceItem.models;

import java.time.LocalDateTime;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "    {\n"+
                "       Id= "+id + "\n" +
                "       Name= "+name+ "\n" +
                "       Price= "+price+ "\n" +
                "       CreatedAt= "+createdAt+ "\n" +
                "    }"+ "\n" ;
    }
}
