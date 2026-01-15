package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Request.basketRequest;
import org.example.Controller.Request.paymentRequest;
import org.example.Entity.basket;
import org.example.Service.basketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class basketController {

    private final basketService basketService;

    @GetMapping("/{id}")
    public ResponseEntity<basket> getBasketById(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PostMapping
    public ResponseEntity<basket> createBasket(@RequestBody basketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<basket> updateBasket(@PathVariable String id, @RequestBody basketRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id, request));
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<basket> payBasket(@PathVariable String id, @RequestBody paymentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id, request));
    }
}