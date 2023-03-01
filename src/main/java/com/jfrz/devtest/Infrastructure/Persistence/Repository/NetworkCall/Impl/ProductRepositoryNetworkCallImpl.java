package com.jfrz.devtest.Infrastructure.Persistence.Repository.NetworkCall.Impl;

import com.jfrz38.test.Domain.Vo.ProductDetailIdVo;
import com.jfrz38.test.Domain.Vo.ProductIdVo;
import com.jfrz38.test.Infrastructure.Persistence.Entity.ProductDetailEntity;
import com.jfrz38.test.Infrastructure.Persistence.IO.CircuitBreaker.CustomCircuitBreaker;
import com.jfrz38.test.Infrastructure.Persistence.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public final class ProductRepositoryNetworkCallImpl implements ProductRepository {

    private final CustomCircuitBreaker customCircuitBreaker;
    @Value("${app.products.url}")
    private String productsUrl;

    @Override
    public Optional<List<Integer>> findSimilarById(ProductIdVo productId) {
        return callGetCircuitBreaker("similarProductsById",
                new StringBuilder()
                        .append(productsUrl)
                        .append(productId.getId())
                        .append("/similarids")
                        .toString(),
                (Class<List<Integer>>) ((Class) List.class));

    }

    @Override
    public Optional<ProductDetailEntity> getDetailById(ProductDetailIdVo productId) {
        return callGetCircuitBreaker("detailById",
                new StringBuilder()
                        .append(productsUrl)
                        .append(productId.getId())
                        .toString(),
                ProductDetailEntity.class);
    }

    private <T> Optional<T> callGetCircuitBreaker(String circuitBreakerName, String url, Class<T> response) {
        // TODO: Mirar los PRs a ver si cojo ideas
        // TODO: Para comprobar que está desacoplado el método de llamada probar a cambiar a Feign a ver cómo es de fácil: https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/
        return customCircuitBreaker.executeGet(circuitBreakerName, url, response);
    }
}
