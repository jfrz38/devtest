package com.jfrz.devtest.Service.Mapping;

import com.jfrz38.test.Domain.Dto.ProductDetailDto;
import com.jfrz38.test.Infrastructure.Persistence.Entity.ProductDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    ProductDetailDto productDetailEntityToDto(ProductDetailEntity productDetail);
}
