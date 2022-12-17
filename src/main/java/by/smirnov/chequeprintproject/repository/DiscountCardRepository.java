package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.DiscountCard;

public interface DiscountCardRepository {

    DiscountCard findById(Long id);
}
