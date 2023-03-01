package com.jfrz.devtest.Domain.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class ProductDetailDto implements Serializable {

    private String id;
    private String name;
    private double price;
    private boolean availability;

}
