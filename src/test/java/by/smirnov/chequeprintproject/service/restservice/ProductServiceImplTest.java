package by.smirnov.chequeprintproject.service.restservice;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import by.smirnov.chequeprintproject.repository.ProductDBRepository;
import by.smirnov.chequeprintproject.service.chequebuilder.ChequeCounter;
import by.smirnov.chequeprintproject.service.chequebuilder.EntityChequeBuilder;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void findByIdTest() {
        Product product = productServiceImpl.findById(1L);
        Mockito.verify(productDBRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void convertCartTest() {
        ProductServiceImpl productService = new ProductServiceImpl(productDBRepository, discountCardService);
        Map<Long, Integer> products = new HashMap<>();
        products.put(1L, 4);
        products.put(2L, 5);
        request = new ChequeRequest(products, 1L, 1001L);
        DiscountCard card = new DiscountCard(
                1L,
                "John",
                "John",
                5.0,
                true,
                Timestamp.valueOf("2022-01-01 00:00:00"));

        Map<Product, Integer> expected = new HashMap<>();
        expected.put(Product.builder()
                .id(1L)
                .productName("Vic Firth drumsticks 2B")
                .price(14.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build(), 4);
        expected.put(Product.builder()
                .id(2L)
                .productName("Vic Firth drumsticks 2BN")
                .price(14.1)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build(), 5);

        Map<Product, Integer> actual = productService.convertCart(products);

        assertEquals(expected, actual);
    }
}
