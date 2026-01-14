package org.example.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.Client.Response.platziProductResponse;
import org.example.Client.platziStoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class productService {

    private final platziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<platziProductResponse> getAllProducts() {
        log.info("Getting all products");
        return platziStoreClient.getAllProducts();

    }

    @Cacheable(value = "product", key = "#id")
    public platziProductResponse getProductById(long id) {
        log.info("Getting product with id: {}", id);
        return platziStoreClient.getProductById(id);

    }
}
