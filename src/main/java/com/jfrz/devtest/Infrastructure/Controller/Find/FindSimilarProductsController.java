package com.jfrz.devtest.Infrastructure.Controller.Find;


import com.jfrz.devtest.Domain.Dto.ProductDetailDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FindSimilarProductsController {
    ResponseEntity<List<ProductDetailDto>> similarProducts(String productId);
}
