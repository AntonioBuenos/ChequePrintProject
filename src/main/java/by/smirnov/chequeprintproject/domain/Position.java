package by.smirnov.chequeprintproject.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
    private int qty;
    private String description;
    private double price;
    private double positionTotal;
}
