package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto,String productId);
    void delete(String productId);
    ProductDto get(String productId);

    PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBY,String sortDir);

    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBY,String sortDir);

    //seacrh
    PageableResponse<ProductDto> seaerchByTitle(String subTitle,int pageNumber,int pageSize,String sortBY,String sortDir);

   //create product with category

    ProductDto createWithCategory(ProductDto productDto,String categoryId);

    //update category of product

    ProductDto updateCategory(String productId,String categoryId);
    PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber,int  pageSize,String sortBy,String sortDir);
}
