package com.jfrz38.test.Infrastructure.Persistence.Repository;

import com.jfrz38.test.Domain.Vo.ProductDetailIdVo;
import com.jfrz38.test.Domain.Vo.ProductIdVo;
import com.jfrz38.test.Infrastructure.Persistence.Entity.ProductDetailEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<List<Integer>> findSimilarById(ProductIdVo productId);

    Optional<ProductDetailEntity> getDetailById(ProductDetailIdVo productId);
}
