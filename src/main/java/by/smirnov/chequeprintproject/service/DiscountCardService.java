package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.domain.DiscountCard;

import java.util.List;

public interface DiscountCardService {

    DiscountCard findById(Long id);

    List<DiscountCard> findAll();
}
