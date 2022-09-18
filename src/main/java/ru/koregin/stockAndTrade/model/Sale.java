package ru.koregin.stockAndTrade.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "doc_num", unique = true, nullable = false)
    private long docNum;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "doc_type", nullable = false)
    private DocType docType;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "type_calc", nullable = false)
    private TypeCalc typeCalc;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ItemSale> itemsSale;

    public enum DocType {
        CASH("Кассовый чек"),
        COMMODITY("Товарный чек");

        private String title;

        DocType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum TypeCalc {
        CASH("Наличный"),
        CASHLESS("Безналичный");

        private String title;

        TypeCalc(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
