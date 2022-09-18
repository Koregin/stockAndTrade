package ru.koregin.stockAndTrade.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "arrivals")
public class Arrival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "doc_num", unique = true, nullable = false)
    private long docNum;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "oper_type", nullable = false)
    private OperationType operationType;

    @Column(name = "date_arrival")
    private ZonedDateTime dateArrival = ZonedDateTime.now();

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @OneToMany(mappedBy = "arrival", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ItemArrival> itemsArrival;

    public enum OperationType {
        ENTERBALANCES("Ввод остатков"),
        ARRIVAL("Приход"),
        REFUND("Возврат");

        private String title;

        OperationType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
