package com.lcwd.electronic.store.dtos;


import com.lcwd.electronic.store.entities.Category;
import lombok.*;

import java.util.Date;

@Data
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class ProductDto {

    private String productId;
    private String title;

    private String description;
    private int discountedPrice;
    private int price;
    private int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String productImageName;
    private CategoryDto category;
}
