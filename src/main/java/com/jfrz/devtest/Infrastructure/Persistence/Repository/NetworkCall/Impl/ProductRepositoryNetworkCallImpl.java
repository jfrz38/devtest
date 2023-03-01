package com.jfrz.devtest.Infrastructure.Persistence.Repository.NetworkCall.Impl;

import com.jfrz.devtest.Domain.Vo.ProductIdVo;
import com.jfrz.devtest.Domain.Vo.ProductDetailIdVo;
import com.jfrz.devtest.Infrastructure.Persistence.Entity.ProductDetailEntity;
import com.jfrz.devtest.Infrastructure.Persistence.IO.CircuitBreaker.CustomCircuitBreaker;
import com.jfrz.devtest.Infrastructure.Persistence.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public final class ProductRepositoryNetworkCallImpl implements ProductRepository {

    @Value("${app.products.url}")
    private String productsUrl;

    private final CustomCircuitBreaker customCircuitBreaker;

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
        return customCircuitBreaker.executeGet(circuitBreakerName, url, response);
    }

}
