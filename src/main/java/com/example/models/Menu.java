package com.example.models;

import com.example.dtos.MenuDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.data.annotation.NamingStrategy;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Serdeable
@Table(name="tb_menu")
@SerdeImport(MenuDTO.class)
@NamingStrategy(NamingStrategies.LowerCase.class)
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String idMenu;
    private String name;
    private Double price = 0.0;
    private int quantity;

    public Menu(Long id,String idMenu, String name, Double price, int quantity) {
        this.id = id;
        this.idMenu = idMenu;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Menu(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
