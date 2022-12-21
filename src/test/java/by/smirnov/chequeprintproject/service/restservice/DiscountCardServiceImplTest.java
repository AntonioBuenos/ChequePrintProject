package by.smirnov.chequeprintproject.service.restservice;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.repository.DiscountCardDBRepository;
import by.smirnov.chequeprintproject.service.restservice.DiscountCardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DiscountCardServiceImplTest {

    @Autowired
    DiscountCardServiceImpl discountCardService;

    @SpyBean
    DiscountCardDBRepository discountCardDBRepository;

    @Test
    void findById() {
        DiscountCard card = discountCardService.findById(1L);
        Mockito.verify(discountCardDBRepository, Mockito.times(1)).findById(1L);
    }
}
