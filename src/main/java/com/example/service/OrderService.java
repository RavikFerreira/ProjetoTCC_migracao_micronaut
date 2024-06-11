package com.example.service;

import com.example.exceptions.ItIsNotAllowedToAddOrdersWithTheSameId;
import com.example.exceptions.OrderResourceNotFoundException;
import com.example.exceptions.TablesResourceNotFoundException;
import com.example.dtos.OrderDTO;
import com.example.models.Menu;
import com.example.models.Order;
import com.example.models.TableBar;
import com.example.models.enums.State;
import com.example.repository.MenuRepository;
import com.example.repository.TableRepository;
import com.example.repository.OrderRepository;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderService {

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private TableRepository tableRepository;
    @Inject
    private MenuRepository menuRepository;

    public List<Order> list(){
        return orderRepository.findAll();

    }
    @Transactional
    public OrderDTO addOrderInMesa(String idTable, String idOrder) throws ItIsNotAllowedToAddOrdersWithTheSameId {
        TableBar tables = tableRepository.findByIdTable(idTable).orElseThrow(() -> new TablesResourceNotFoundException(idTable));
        tables.setState(State.OCUPADO);
        List<Order> orders = tables.getOrders();
        for(Order order : orders){
            if(order.getIdOrder().equals(idOrder)){
                throw new ItIsNotAllowedToAddOrdersWithTheSameId(idOrder);
            }
        }
        Order newOrder = new Order();
        newOrder.setIdOrder(idOrder);
        orders.add(newOrder);

        tableRepository.save(tables);
        return new OrderDTO(newOrder);
    }

    @Transactional
    public OrderDTO delete(String idTable, String idOrder, String idMenu){
        TableBar tables = tableRepository.findByIdTable(idTable).orElseThrow(() -> new TablesResourceNotFoundException(idTable));
        Order order = orderRepository.findByIdOrder(idOrder).orElseThrow(()-> new OrderResourceNotFoundException(idOrder));
//        if (!tables.getOrder().getIdOrder().equals(idOrder)) {
//            throw new OrderResourceNotFoundException(idOrder);
//        }

        List<Order> orders = tables.getOrders();
        for(Order order1 : orders){
            List<Menu> products = order1.getMenu();
            for(Menu product : products){
                if(product.getIdMenu().equals(idMenu)){
                    int count = product.getQuantity() - 1;
                    product.setQuantity(count);
                    if(count < 1){
                        products.remove(product);
                    }
                }
                break;
            }
        }
        orderRepository.save(order);
        return new OrderDTO(order);
    }
}
