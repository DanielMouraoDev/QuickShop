package org.example.Controller.Request;

import java.util.List;

public record basketRequest(Long clientId, List<productRequest> products) {
}
