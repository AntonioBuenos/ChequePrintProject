package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.repository.ProductDBRepository;
import by.smirnov.chequeprintproject.service.restservice.DiscountCardServiceImpl;
import by.smirnov.chequeprintproject.service.restservice.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @SpyBean
    ProductDBRepository productDBRepository;

    @SpyBean
    DiscountCardServiceImpl discountCardService;

    @Spy
    ChequeRequest request;

    @Test
    void findById() {
        Product product = productServiceImpl.findById(1L);
        Mockito.verify(productDBRepository, Mockito.times(1)).findById(1L);
    }

}
