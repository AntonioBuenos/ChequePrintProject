package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ProductSetRepositoryImplTest {

    private static Set<Product> products = new TreeSet<>();
    private ProductRepository repository;

    @BeforeAll
    static void init(){
        products.add(Product.builder()
                .id(1L)
                .productName("Vic Firth drumsticks 2B")
                .price(14.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build());
        products.add(Product.builder()
                .id(2L)
                .productName("Vic Firth drumsticks 2BN")
                .price(14.1)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build());
        products.add(Product.builder()
                .id(3L)
                .productName("Vic Firth drumsticks 5B")
                .price(14.1)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build());
    }

    @Test
    void findByIdTest() {
        repository = new ProductSetRepositoryImpl();
        Product expected = Product.builder()
                .id(3L)
                .productName("Vic Firth drumsticks 5B")
                .price(14.1)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build();
        Product actual = repository.findById(3L);
        assertEquals(expected, actual);
    }
}
