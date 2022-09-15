package ru.koregin.stockAndTrade.model;

import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_name")
    private String customerName;
}
