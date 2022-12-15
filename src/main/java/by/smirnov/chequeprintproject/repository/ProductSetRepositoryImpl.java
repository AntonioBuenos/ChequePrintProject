package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.Product;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class ProductSetRepositoryImpl implements ProductRepository {

    @Override
    public Product findById(Long id) {
    return products.stream()
            .filter(p -> Objects.equals(p.getId(), id))
            .findFirst()
            .orElse(null);
    }

    public static final Set<Product> products = new TreeSet<>();

    static {
        products.add(Product.builder()
                .id(1L)
                .productName("Vic Firth drumsticks 2B")
                .price(14.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(2L)
                .productName("Vic Firth drumsticks 2BN")
                .price(14.1)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(3L)
                .productName("Vic Firth drumsticks 5B")
                .price(14.1)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(4L)
                .productName("Vic Firth Signature Series Kenny Aronoff")
                .price(17.0)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(5L)
                .productName("Vic Firth Signature Series Terry Bozzio")
                .price(17.0)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(6L)
                .productName("Vic Firth Signature Series JoJo Mayer")
                .price(17.0)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(7L)
                .productName("Vic Firth Signature Series Nicko McBrain")
                .price(17.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(8L)
                .productName("Vic Firth Signature Series Mike Terrana")
                .price(17.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(9L)
                .productName("Vic Firth Signature Series Dave Weckl")
                .price(17.0)
                .isPromoted(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
        products.add(Product.builder()
                .id(10L)
                .productName("Drummaster drumsticks 2B USA Hickory")
                .price(9.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .terminationDate(null).build());
    }
}
