package by.smirnov.chequeprintproject.domain;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Cheque {

private Map<Long, Integer>chosenGoods;
private Date date;
}
