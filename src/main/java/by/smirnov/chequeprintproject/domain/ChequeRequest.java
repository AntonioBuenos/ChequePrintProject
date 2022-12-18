package by.smirnov.chequeprintproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ChequeRequest {

private Map<Long, Integer> products;
private Long cardId;
private Long cashierId;
}
