package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardSetRepositoryImplTest {

    private static Set<DiscountCard> cards = new TreeSet<>();
    private DiscountCardRepository repository;

    @BeforeAll
    static void init(){
        cards.add(DiscountCard.builder()
                .id(1L)
                .holderEmail("1001@supermail.com")
                .holderName("John Smith")
                .discountRate(3.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:38"))
                .build());
        cards.add(DiscountCard.builder()
                .id(2L)
                .holderEmail("1002@supermail.com")
                .holderName("Jane Smith")
                .discountRate(3.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:39"))
                .build());
        cards.add(DiscountCard.builder()
                .id(3L)
                .holderEmail("1003@supermail.com")
                .holderName("John Doe")
                .discountRate(5.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:40"))
                .build());
        cards.add(DiscountCard.builder()
                .id(4L)
                .holderEmail("1004@supermail.com")
                .holderName("Jane Doe")
                .discountRate(5.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:41"))
                .build());
        cards.add(DiscountCard.builder()
                .id(5L)
                .holderEmail("1005@supermail.com")
                .holderName("Mescalero El Camino")
                .discountRate(7.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:42"))
                .build());
    }

    @Test
    void findByIdTest() {
        repository = new DiscountCardSetRepositoryImpl();
        DiscountCard expected = DiscountCard.builder()
                .id(5L)
                .holderEmail("1005@supermail.com")
                .holderName("Mescalero El Camino")
                .discountRate(7.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:42"))
                .build();
        DiscountCard actual = repository.findById(5L);
        assertEquals(expected, actual);
    }
}
