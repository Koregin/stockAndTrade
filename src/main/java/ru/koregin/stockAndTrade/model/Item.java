package ru.koregin.stockAndTrade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Column(name = "sale_price")
    private double salePrice;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "article", nullable = false)
    private String article;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "barcode")
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "item_group_id", nullable = false)
    private ItemGroup itemGroup;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
}
