package ru.koregin.stockAndTrade.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "items_from_sale")
public class ItemFromSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private int amount;

    @Column(name = "article")
    private String article;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    @JsonBackReference
    private Sale sale;
}
