package com.example.controller;

import com.example.dtos.MenuDTO;
import com.example.exceptions.ItIsNotPossibleToAddAProductToTheMenuWithTheSameId;
import com.example.exceptions.OrderResourceNotFoundException;
import com.example.exceptions.UnableToEditAnOrderFromATable;
import com.example.models.Menu;
import com.example.service.MenuService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.List;

@Controller("menu/")
public class MenuController {

    @Inject
    private MenuService menuService;

    @Get("list")
    public HttpResponse<List<Menu>> listOrders(){
        return HttpResponse.ok(menuService.menuList());
    }

    @Post("addMenu")
    public HttpResponse<MenuDTO> addMenu(@Body Menu menu) throws ItIsNotPossibleToAddAProductToTheMenuWithTheSameId {
        MenuDTO menuDto = menuService.addMenu(menu);
        return HttpResponse.created(menuDto);
    }
    @Get("searchProduct/{idMenu}")
    public HttpResponse<MenuDTO> search(@PathVariable String idMenu){
        MenuDTO product = menuService.searchProduct(idMenu);
        return HttpResponse.ok(product);
    }
    @Post("addMenuInOrder/{idTable}/{idOrder}/{idMenu}")
    public HttpResponse<MenuDTO> addMenuInOrder(@PathVariable String idTable, @PathVariable String idOrder, @PathVariable String idMenu ) throws OrderResourceNotFoundException {
        MenuDTO menu = menuService.addMenuInOrder(idTable, idOrder, idMenu);
        return HttpResponse.created(menu);
    }
    @Patch("updateOrderInMenu/{idMenu}")
    public HttpResponse<MenuDTO> updateOrderInMenu(@PathVariable String idMenu, @Body Menu menu) throws UnableToEditAnOrderFromATable {
        MenuDTO products = menuService.updateOrderInMenu(idMenu, menu);
        return HttpResponse.ok(products);
    }

    @Delete("delete/{idMenu}")
    public HttpResponse<MenuDTO> delete(@PathVariable String idMenu){
        menuService.delete(idMenu);
        return HttpResponse.ok();
    }
}
