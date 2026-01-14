package org.example.Client.Response;

import java.io.Serializable;
import java.math.BigDecimal;

public record platziProductResponse (Long id, String title, BigDecimal price) implements Serializable {
}
