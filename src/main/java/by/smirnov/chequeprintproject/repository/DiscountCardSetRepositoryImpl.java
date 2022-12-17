package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.DiscountCard;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiscountCardSetRepositoryImpl implements DiscountCardRepository {
    @Override
    public DiscountCard findById(Long id) {
        return cards.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private static final Set<DiscountCard> cards = new TreeSet<>();

    static {
        cards.add(DiscountCard.builder()
                .id(1001L)
                .holderEmail("1001@supermail.com")
                .holderName("John Smith")
                .discountRate(3.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:38"))
                .build());
        cards.add(DiscountCard.builder()
                .id(1002L)
                .holderEmail("1002@supermail.com")
                .holderName("Jane Smith")
                .discountRate(3.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:39"))
                .build());
        cards.add(DiscountCard.builder()
                .id(1003L)
                .holderEmail("1003@supermail.com")
                .holderName("John Doe")
                .discountRate(5.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:40"))
                .build());
        cards.add(DiscountCard.builder()
                .id(1004L)
                .holderEmail("1004@supermail.com")
                .holderName("Jane Doe")
                .discountRate(5.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:41"))
                .build());
        cards.add(DiscountCard.builder()
                .id(1005L)
                .holderEmail("1005@supermail.com")
                .holderName("Mescalero El Camino")
                .discountRate(7.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf("2022-12-17 23:49:42"))
                .build());
    }
}
