package com.example.dtos;

import com.example.models.Order;
import com.example.models.TableBar;
import com.example.models.enums.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TablesDTO {
    private String idTable;
    private List<Order> orders;
    private double account;
    private State state;

    public TablesDTO(TableBar tableBar) {
        idTable = tableBar.getIdTable();
        orders =  tableBar.getOrders();
        account = tableBar.getAccount();
        state = tableBar.getState();
    }

}
