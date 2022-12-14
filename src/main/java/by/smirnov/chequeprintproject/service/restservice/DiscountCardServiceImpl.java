package by.smirnov.chequeprintproject.service.restservice;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.repository.DiscountCardDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService{

    private final DiscountCardDBRepository repository;

    @Override
    public DiscountCard findById(Long id) {
        return repository
                .findById(id)
                .orElse(null);
    }

}
