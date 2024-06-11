package com.example.dtos;

import com.example.models.Menu;
import com.example.models.Order;
import com.example.models.TableBar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
    private String idOrder;
    private List<Menu> menu;

    public OrderDTO(Order order) {
        idOrder = order.getIdOrder();
        menu = order.getMenu();

    }
}
