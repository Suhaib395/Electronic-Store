package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {

    private String categoryId;
    @NotBlank
    @Size(min = 4,message = "title must be min 4 character")
    private String title;
    @NotBlank(message = "description required!!")
    private String decription;

    private String coverImage;

//    List<Product> products=new ArrayList<>();

}
