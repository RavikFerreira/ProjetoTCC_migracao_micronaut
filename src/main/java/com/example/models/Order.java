package com.example.models;

import com.example.dtos.OrderDTO;

import io.micronaut.data.annotation.NamingStrategy;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Serdeable
@SerdeImport(OrderDTO.class)
@NamingStrategy(NamingStrategies.LowerCase.class)
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    private String idOrder;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_menu")
    )
    private List<Menu> menu;

    public Order(Long id, String idOrder, List<Menu> menu) {
        this.id = id;
        this.idOrder = idOrder;
        this.menu = new ArrayList<>();
    }
    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public List<Menu> getMenu() {
        return menu;
    }
    public void setMenu( List<Menu> menu) {
        this.menu = menu;
    }
}
