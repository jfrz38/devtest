package com.jfrz.devtest.Infrastructure.Persistence.IO.CircuitBreaker.Resilience4J.Impl;

import com.jfrz.devtest.Infrastructure.Persistence.IO.CircuitBreaker.CustomCircuitBreaker;
import com.jfrz.devtest.Infrastructure.Persistence.IO.NetworkCall.NetworkCall;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class CircuitBreakerResilience4JImpl implements CustomCircuitBreaker {

    private final NetworkCall networkApiCall;

    private final CircuitBreakerRegistry circuitBreakerRegistry;


    @Override
    public <T> Optional<T> executeGet(String circuitBreakerName, String url, Class<T> response) {
        try {
            return circuitBreakerRegistry.circuitBreaker(circuitBreakerName)
                    .executeCallable(() -> Optional.ofNullable(networkApiCall.callGetApi(url, response)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
