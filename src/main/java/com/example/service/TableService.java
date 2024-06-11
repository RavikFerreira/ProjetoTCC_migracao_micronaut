package com.example.service;

import com.example.exceptions.CannotCreateATableWithTheSameId;
import com.example.dtos.TablesDTO;
import com.example.exceptions.CannotDeleteABusyTable;
import com.example.exceptions.TablesResourceNotFoundException;
import com.example.models.Menu;
import com.example.models.Order;
import com.example.models.TableBar;
import com.example.models.enums.State;
import com.example.repository.TableRepository;
import com.example.repository.OrderRepository;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@Singleton
public class TableService {

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private TableRepository tableRepository;

    public List<TableBar> list(){
        List<TableBar> tableBars = tableRepository.findAll();

        for(TableBar tableBar : tableBars){
            double account = 0.0;
            List<Order> orders = tableBar.getOrders();
            if(orders != null) {
                for(Order order : orders){
                    List<Menu> products = order.getMenu();
                    for (Menu menu : products) {
                        account += menu.getPrice() * menu.getQuantity();
                    }
                    tableBar.setAccount(account);
                }
            }
        }
        return tableBars;
    }
    @Transactional
    public TablesDTO addTables(TableBar tables) throws CannotCreateATableWithTheSameId {
        Optional<TableBar> tableExists = tableRepository.findByIdTable(tables.getIdTable());
        if(tableExists.isPresent()) {
            throw new CannotCreateATableWithTheSameId(tables.getIdTable());
        }

        tables.setState(State.LIVRE);
        tableRepository.save(tables);

        return new TablesDTO(tables);
    }

    public TableBar search(String table){
        TableBar tables = tableRepository.findByIdTable(table).orElseThrow(() -> new TablesResourceNotFoundException(table));
        List<Order> orders = tables.getOrders();
        for(Order order : orders){
            double account = 0.0;
            List<Menu> products = order.getMenu();
                for(Menu menu : products){
                account += menu.getPrice() * menu.getQuantity();
            }
            tables.setAccount(account);
        }
        return tables;
    }

    public TablesDTO delete(String idTable) throws CannotDeleteABusyTable {
        TableBar tables =tableRepository.findByIdTable(idTable).orElseThrow(()-> new TablesResourceNotFoundException(idTable));
        if(tables.getState() == State.OCUPADO){
          throw new CannotDeleteABusyTable(idTable);
        } if(tables != null){
            tableRepository.delete(tables);
        }
        return new TablesDTO(tables);
    }

}
