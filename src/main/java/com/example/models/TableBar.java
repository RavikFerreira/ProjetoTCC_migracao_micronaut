package com.example.models;

import com.example.dtos.TablesDTO;
import com.example.models.enums.State;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.micronaut.data.annotation.NamingStrategy;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_table_bar")
@Serdeable
@SerdeImport(TablesDTO.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamingStrategy(NamingStrategies.LowerCase.class)
public class TableBar implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_bar_id")
    private Long id;
    private String idTable;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    private double account = 0.0;
    @Enumerated(EnumType.STRING)
    private State state;

    public TableBar(Long id, String idTable, List<Order> orders, double account, State state) {
        this.id = id;
        this.idTable = idTable;
        this.orders = orders;
        this.account = account;
        this.state = state;

    }
    public TableBar(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

