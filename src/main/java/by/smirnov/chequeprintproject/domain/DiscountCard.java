package by.smirnov.chequeprintproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "discount_card", schema = "chequebase")
public class DiscountCard implements Comparable<DiscountCard>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "holder_email")
    private String holderEmail;

    @Column(name = "discount_rate")
    private Double discountRate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Override
    public int compareTo(DiscountCard o) {
        return id.compareTo(o.getId());
    }
}
