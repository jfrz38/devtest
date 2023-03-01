package com.jfrz.devtest.Infrastructure.Controller.Find;

import com.jfrz.devtest.Domain.Dto.ProductDetailDto;
import com.jfrz.devtest.Domain.Vo.ProductIdVo;
import com.jfrz.devtest.Infrastructure.Controller.SimilarProductsControllerImpl;
import com.jfrz.devtest.Service.Find.FindSimilarProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public final class FindSimilarProductsControllerImpl extends SimilarProductsControllerImpl implements FindSimilarProductsController {

    private final FindSimilarProductService findSimilarProductService;

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetailDto>> similarProducts(@PathVariable String productId) {
        return ResponseEntity.ok(findSimilarProductService.findSimilarProducts(new ProductIdVo(productId)));
    }
}
