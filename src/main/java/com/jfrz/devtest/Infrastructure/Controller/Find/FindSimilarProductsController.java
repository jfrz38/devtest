package com.jfrz.devtest.Infrastructure.Controller.Find;

import com.jfrz38.test.Domain.Dto.ProductDetailDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FindSimilarProductsController {
    ResponseEntity<List<ProductDetailDto>> similarProducts(String productId);
}
