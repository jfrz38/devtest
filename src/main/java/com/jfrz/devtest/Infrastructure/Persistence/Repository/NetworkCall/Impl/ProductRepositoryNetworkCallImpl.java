package com.jfrz.devtest.Infrastructure.Persistence.Repository.NetworkCall.Impl;

import com.jfrz.devtest.Domain.Vo.ProductIdVo;
import com.jfrz.devtest.Domain.Vo.ProductDetailIdVo;
import com.jfrz.devtest.Infrastructure.Persistence.Entity.ProductDetailEntity;
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

    @Override
    public Optional<List<Integer>> findSimilarById(ProductIdVo productId) {
        return Optional.ofNullable(null);

    }

    @Override
    public Optional<ProductDetailEntity> getDetailById(ProductDetailIdVo productId) {
        return Optional.ofNullable(null);
    }

}
