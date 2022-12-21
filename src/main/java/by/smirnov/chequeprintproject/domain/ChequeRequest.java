package by.smirnov.chequeprintproject.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ChequeRequest {

private Map<Long, Integer> products;
private Long cardId;
private Long cashierId;
}
