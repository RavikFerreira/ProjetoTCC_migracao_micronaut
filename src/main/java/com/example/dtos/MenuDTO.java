package com.example.dtos;

import com.example.models.Menu;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuDTO{

    private String idMenu;
    private String name;
    private Double price;
    private int quantity;

    public MenuDTO(Menu menu){
        idMenu = menu.getIdMenu();
        name = menu.getName();
        price = menu.getPrice();
        quantity = menu.getQuantity();
    }
}
