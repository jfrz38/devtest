package com.jfrz.devtest.Service.Find.Impl;

import com.jfrz.devtest.Domain.Dto.ProductDetailDto;
import com.jfrz.devtest.Domain.Vo.ProductDetailIdVo;
import com.jfrz.devtest.Domain.Vo.ProductIdVo;
import com.jfrz.devtest.Infrastructure.Persistence.Repository.ProductRepository;
import com.jfrz.devtest.Service.Find.FindSimilarProductService;
import com.jfrz.devtest.Service.Mapping.ProductDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public final class FindSimilarProductsServiceImpl implements FindSimilarProductService {

    private final ProductRepository productRepository;

    private final ProductDetailMapper productDetailMapper;

    @Override
    public List<ProductDetailDto> findSimilarProducts(ProductIdVo productId) {
        return productRepository.findSimilarById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Not found similar products for product " + productId.getId()))
                .stream()
                .map(product -> productRepository.getDetailById(new ProductDetailIdVo(product))
                        .map(productDetailMapper::productDetailEntityToDto)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Not found product " + product)))
                .distinct()
                .collect(Collectors.toList());
    }
}
