package ru.koregin.stockAndTrade.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "arrivals")
public class Arrival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "doc_num", unique = true)
    private long docNum;

    @Column(name = "total_amount")
    private double totalAmount;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "oper_type_id")
    private OperationType operationType;

    @Column(name = "date_arrival")
    private ZonedDateTime dateArrival = ZonedDateTime.now();

    @NonNull
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @NonNull
    @OneToMany(mappedBy = "arrival", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ItemFromArrival> itemsFromArrival;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Arrival arrival = (Arrival) o;
        return id != 0 && Objects.equals(id, arrival.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
