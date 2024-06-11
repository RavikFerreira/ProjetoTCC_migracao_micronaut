package com.example.service;

import com.example.dtos.MenuDTO;
import com.example.exceptions.ItIsNotPossibleToAddAProductToTheMenuWithTheSameId;
import com.example.exceptions.OrderResourceNotFoundException;
import com.example.exceptions.TablesResourceNotFoundException;
import com.example.models.Menu;
import com.example.models.Order;
import com.example.models.TableBar;
import com.example.repository.MenuRepository;
import com.example.repository.OrderRepository;
import com.example.repository.TableRepository;

import io.micronaut.cache.annotation.Cacheable;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class MenuService {

    @Inject
    private MenuRepository menuRepository;
    @Inject
    private OrderRepository orderRepository;
    @Inject
    private TableRepository tableRepository;

    @Cacheable("products")
    public List<Menu> menuList(){
        return menuRepository.findAll();
    }

    public MenuDTO addMenu(Menu menu) throws ItIsNotPossibleToAddAProductToTheMenuWithTheSameId {
        Optional<Menu> orders = menuRepository.findByIdMenu(menu.getIdMenu());
        if(orders.isPresent()){
            throw new ItIsNotPossibleToAddAProductToTheMenuWithTheSameId(menu.getIdMenu());
        }
        menu.setIdMenu(menu.getIdMenu());
        menu.setQuantity(1);
        menuRepository.save(menu);
        return new MenuDTO(menu);
    }
    public MenuDTO searchProduct(String idMenu){
        Menu menu = menuRepository.findByIdMenu(idMenu).orElseThrow(() -> new OrderResourceNotFoundException(idMenu));
        return new MenuDTO(menu);
    }

    @Transactional
    public MenuDTO addMenuInOrder(String idTable, String idOrder, String idMenu ) {
        TableBar tables = tableRepository.findByIdTable(idTable).orElseThrow(() -> new TablesResourceNotFoundException(idTable));
        Order order = orderRepository.findByIdOrder(idOrder).orElseThrow(() -> new OrderResourceNotFoundException(idOrder));
        Menu menu = menuRepository.findByIdMenu(idMenu).orElseThrow(() -> new OrderResourceNotFoundException(idMenu));

        List<Order> orders = tables.getOrders();
        boolean orderNotExists = false;

        for(Order order1 : orders) {
            List<Menu> products = order1.getMenu();
            for (Menu product : products) {
                if (product.getIdMenu().equals(menu.getIdMenu())) {
                    product.setQuantity(product.getQuantity() + 1);
                    orderNotExists = true;
                    break;
                }
            }
            if (!orderNotExists) {
                menu.setQuantity(1);
                products.add(menu);
            }
        }
        return new MenuDTO(menu);
    }

    public MenuDTO updateOrderInMenu(String idMenu, Menu menu) {
        searchProduct(idMenu);
        menu.setIdMenu(menu.getIdMenu());
        menu.setName(menu.getName());
        menu.setPrice(menu.getPrice());
        menuRepository.save(menu);
        return new MenuDTO(menu);
    }

    public MenuDTO delete(String idMenu) {
        Menu menu = menuRepository.findByIdMenu(idMenu).orElseThrow(() -> new OrderResourceNotFoundException(idMenu));
        menuRepository.delete(menu);
        return new MenuDTO(menu);
    }
}
