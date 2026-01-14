package org.example.Controller;

import org.example.Client.Response.platziProductResponse;
import org.example.Service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/products")
@RequiredArgsConstructor
public class productController {

    private final productService productService;

    @GetMapping
    public ResponseEntity getAllProducts () {
        return ResponseEntity.ok(productService.getAllProducts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<platziProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}




