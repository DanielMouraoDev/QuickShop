package org.example.Service;

import ch.qos.logback.core.status.Status;
import lombok.RequiredArgsConstructor;
import org.example.Client.Response.platziProductResponse;
import org.example.Controller.Request.basketRequest;
import org.example.Controller.Request.paymentRequest;
import org.example.Entity.basket;
import org.example.Entity.product;
import org.example.Entity.status;
import org.example.Repository.basketRepository;
import org.example.exceptions.businessException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class basketService {

    private final basketRepository basketRepository;
    private final productService productService;

    public basket getBasketById(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Basket not found."));
    }

    public basket createBasket(basketRequest basketRequest) {
        basketRepository.findByClientAndStatus(basketRequest.clientId(), status.OPEN)
                .ifPresent(basket -> {
                    throw new businessException("There is already a basket open for this client");
                });

        List<product> products = getProducts(basketRequest);

        basket newBasket = org.example.Entity.basket.builder()
                .client(basketRequest.clientId())
                .status(status.OPEN)
                .products(products)
                .build();

        newBasket.calculateTotalPrice();

        return basketRepository.save(newBasket);
    }


    public basket updateBasket(String basketId, basketRequest request) {
        basket savedBasket = this.getBasketById(basketId);

        List<product> products = getProducts(request);

        savedBasket.setProducts(products);
        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);
    }

    public basket payBasket(String basketId, paymentRequest request) {
        basket savedBasket = getBasketById(basketId);
        savedBasket.setPaymentMethod(request.getPaymentMethod());
        savedBasket.setStatus(status.SOLD);
        return basketRepository.save(savedBasket);
    }

    public void deleteBasket(String id) {
        basketRepository.delete(getBasketById(id));
    }

    private List<product> getProducts(basketRequest basketRequest) {
        List<product> products = new ArrayList<>();
        basketRequest.products().forEach(reqItem -> {
            platziProductResponse response = productService.getProductById(reqItem.id());

            products.add(product.builder()
                    .id(response.id())
                    .title(response.title())
                    .price(response.price())
                    .quantity(reqItem.quantity())
                    .build());
        });
        return products;
    }
}