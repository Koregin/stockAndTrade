package ru.koregin.stockAndTrade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item_groups")
public class ItemGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "group_name")
    private String groupName;
}
