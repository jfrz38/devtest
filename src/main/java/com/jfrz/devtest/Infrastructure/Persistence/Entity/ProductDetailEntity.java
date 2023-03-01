package com.jfrz38.test.Infrastructure.Persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProductDetailEntity {

    private String id;
    private String name;
    private double price;
    private boolean availability;

}
