package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.exception.NoSuchEntityException;
import by.smirnov.chequeprintproject.repository.DiscountCardDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService{

    private final DiscountCardDBRepository repository;

    @Override
    public DiscountCard findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(NoSuchEntityException::new);
    }

    @Override
    public List<DiscountCard> findAll() {
        return repository.findAll();
    }
}
