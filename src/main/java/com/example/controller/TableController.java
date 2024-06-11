package com.example.controller;

import com.example.exceptions.CannotCreateATableWithTheSameId;
import com.example.exceptions.CannotDeleteABusyTable;
import com.example.exceptions.TablesResourceNotFoundException;
import com.example.dtos.TablesDTO;
import com.example.models.TableBar;
import com.example.service.TableService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import jakarta.inject.Inject;

import java.util.List;

@Controller("tables/")
public class TableController {
    @Inject
    private TableService tableService;

    @Get("list")
    public HttpResponse<List<TableBar>> listOrders(){
        return HttpResponse.ok(tableService.list());
    }

    @Post("create")
    public HttpResponse<TablesDTO> addTables(@Body TableBar tableBar) throws TablesResourceNotFoundException, CannotCreateATableWithTheSameId {
        TablesDTO addTables = tableService.addTables(tableBar);
        return HttpResponse.created(addTables);
    }

    @Get("search/{idTable}")
    public HttpResponse<TableBar> search(@PathVariable String idTable){
        TableBar tables = tableService.search(idTable);
        return HttpResponse.ok(tables);
    }

    @Delete("delete/{idTable}")
    public HttpResponse<TablesDTO> delete(@PathVariable String idTable) throws CannotDeleteABusyTable {
        tableService.delete(idTable);
        return HttpResponse.ok();
    }
}
