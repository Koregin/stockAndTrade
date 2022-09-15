package ru.koregin.stockAndTrade.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @Column(name = "article")
    private String article;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "barcode")
    private String barcode;
    @ManyToOne
    @JoinColumn(name = "item_group_id")
    private ItemGroup itemGroup;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Item item = (Item) o;
        return id != 0 && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
