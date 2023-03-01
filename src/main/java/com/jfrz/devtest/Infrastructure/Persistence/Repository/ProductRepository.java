package com.jfrz.devtest.Infrastructure.Persistence.Repository;

import com.jfrz.devtest.Domain.Vo.ProductDetailIdVo;
import com.jfrz.devtest.Domain.Vo.ProductIdVo;
import com.jfrz.devtest.Infrastructure.Persistence.Entity.ProductDetailEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<List<Integer>> findSimilarById(ProductIdVo productId);

    Optional<ProductDetailEntity> getDetailById(ProductDetailIdVo productId);
}
