package com.jfrz.devtest.Service.Find;

import com.jfrz.devtest.Domain.Dto.ProductDetailDto;
import com.jfrz.devtest.Domain.Vo.ProductIdVo;

import java.util.List;

public interface FindSimilarProductService {

    List<ProductDetailDto> findSimilarProducts(ProductIdVo productId);
}
