package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DiscountCardDBRepository extends
        CrudRepository<DiscountCard, Long>,
        JpaRepository<DiscountCard, Long> {
}
