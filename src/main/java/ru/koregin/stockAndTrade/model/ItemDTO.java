package ru.koregin.stockAndTrade.model;

import lombok.Data;

@Data
public class ItemDTO {
    private long id;

    private double purchasePrice;

    private double salePrice;

    private int stockQuantity;

    private String article;

    private String itemName;

    private String barcode;

    private String groupName;

    private String stock;
}
