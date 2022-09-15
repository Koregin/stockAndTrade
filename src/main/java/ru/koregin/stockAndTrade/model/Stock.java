package ru.koregin.stockAndTrade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stock_name")
    private String stockName;
}
