package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "basket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class basket {

    @Id
    private String id;

    private long client;

    private BigDecimal totalPrice;

    private List<product> products;

    private status status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private paymentMethod paymentMethod;

    public void calculateTotalPrice() {

        if (this.products == null || this.products.isEmpty()) {
            this.totalPrice = BigDecimal.ZERO;
            return;
        }

        this.totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}