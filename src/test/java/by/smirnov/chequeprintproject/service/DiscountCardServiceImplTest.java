package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.repository.DiscountCardDBRepository;
import by.smirnov.chequeprintproject.repository.ProductDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
