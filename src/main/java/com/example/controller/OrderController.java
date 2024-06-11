package com.example.controller;

import com.example.exceptions.ItIsNotAllowedToAddOrdersWithTheSameId;
import com.example.exceptions.OrderResourceNotFoundException;
import com.example.dtos.OrderDTO;
import com.example.models.Order;
import com.example.service.OrderService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import jakarta.inject.Inject;

import java.util.List;

@Controller("order/")
public class OrderController {

    @Inject
    private OrderService orderService;

    @Get("list")
    public HttpResponse<List<Order>> listOrder(){
        return HttpResponse.ok(orderService.list());
    }


    @Post("addOrderInTable/{idTable}/{idOrder}")
    public HttpResponse<OrderDTO> addOrderInTable(@PathVariable String idTable, @PathVariable String idOrder) throws OrderResourceNotFoundException, ItIsNotAllowedToAddOrdersWithTheSameId {
        OrderDTO order = orderService.addOrderInMesa(idTable, idOrder);
        return HttpResponse.created(order);
    }

    @Delete("removeOrderInTable/{idTable}/{idOrder}/{idMenu}")
    public OrderDTO delete(@PathVariable String idTable, @PathVariable String idOrder, @PathVariable String idMenu){
        OrderDTO order = orderService.delete(idTable, idOrder, idMenu);
        return HttpResponse.ok(order).body();
    }
}
