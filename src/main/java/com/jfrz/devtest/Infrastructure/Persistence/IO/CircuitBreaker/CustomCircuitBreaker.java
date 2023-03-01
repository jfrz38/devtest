package com.jfrz.devtest.Infrastructure.Persistence.IO.CircuitBreaker;

import java.util.Optional;

public interface CustomCircuitBreaker {

    <T> Optional<T> executeGet(String circuitBreakerName, String url, Class<T> response);

}
