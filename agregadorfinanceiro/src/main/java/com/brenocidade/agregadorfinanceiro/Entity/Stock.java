package com.brenocidade.agregadorfinanceiro.entity;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "tb_stock")
public class Stock {

    @Id
    @Column(name = "stock_id")
    private UUID stockId;

    @Column(name = "description")
    private String description;

}
