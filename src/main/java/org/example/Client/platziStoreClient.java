package org.example.Client;

import org.example.Client.Response.platziProductResponse;
import org.example.exceptions.customErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}", configuration = { customErrorDecoder.class })
public interface platziStoreClient {

    @GetMapping("/products")
    List<platziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    platziProductResponse getProductById(@PathVariable Long id);
}
