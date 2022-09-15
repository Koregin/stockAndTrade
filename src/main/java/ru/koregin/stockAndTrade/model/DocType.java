package ru.koregin.stockAndTrade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doc_types")
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type_name")
    private String typeName;
}
