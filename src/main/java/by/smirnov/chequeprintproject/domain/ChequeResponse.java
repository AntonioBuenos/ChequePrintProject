package by.smirnov.chequeprintproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ChequeResponse {
    private String title;
    private String storeName;
    private String address;
    private String phoneNumber;
    private Long cashierNumber;
    @EqualsAndHashCode.Exclude
    private LocalDate date;
    @EqualsAndHashCode.Exclude
    private LocalTime time;

    private List<Position> positions;

    private Double sum;
    private Double promoDiscount;
    private Double cardDiscount;
    private Double taxable;
    private Double vat;
    private Double total;
    private String ad;
}
