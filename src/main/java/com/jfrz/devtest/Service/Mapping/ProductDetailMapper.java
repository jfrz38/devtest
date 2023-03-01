package com.jfrz.devtest.Service.Mapping;

import com.jfrz.devtest.Domain.Dto.ProductDetailDto;
import com.jfrz.devtest.Infrastructure.Persistence.Entity.ProductDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    ProductDetailDto productDetailEntityToDto(ProductDetailEntity productDetail);
}
