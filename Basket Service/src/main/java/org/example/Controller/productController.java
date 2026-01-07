package org.example.Controller;

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

    private final productService productService

    @GetMapping
    public ResponseEntity<Void> getAllProducts() {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok().build();
    }
}




