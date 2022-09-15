package ru.koregin.stockAndTrade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "doc_num", unique = true)
    private long docNum;

    @Column(name = "price")
    private double price;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "quantity")
    private int quantity;


    private String article;
    private DocType docType;
    private Customer customer;
    private TypeCalc typeCalc;
    private Employee employee;
}
