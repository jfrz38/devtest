package com.jfrz38.test.Infrastructure.Controller.Find;

import com.jfrz38.test.Domain.Dto.ProductDetailDto;
import com.jfrz38.test.Domain.Vo.ProductIdVo;
import com.jfrz38.test.Infrastructure.Controller.SimilarProductsControllerImpl;
import com.jfrz38.test.Service.Find.FindSimilarProductService;
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
        // TODO: Mirar si es un objeto con una lista o directamente la lista lo que se devuelve
        return ResponseEntity.ok(findSimilarProductService.findSimilarProducts(new ProductIdVo(productId)));
    }
}
