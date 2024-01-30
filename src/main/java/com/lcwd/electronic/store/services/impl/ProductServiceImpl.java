package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;
import com.lcwd.electronic.store.entities.Category;
import com.lcwd.electronic.store.entities.Product;
import com.lcwd.electronic.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.CategoryRepositry;
import com.lcwd.electronic.store.repositories.ProductRepository;
import com.lcwd.electronic.store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepositry categoryRepositry;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        String productId = UUID.randomUUID().toString();
        productDto.setProductId(productId);
        productDto.setAddedDate(new Date());
        var product = mapper.map(productDto, Product.class);
        var saveProduct = productRepository.save(product);
        return mapper.map(saveProduct, ProductDto.class);
    }
//    private String title;
//
//    private String description;
//    private int discountedPrice;
//    private int price;
//    private int quantity;
//    private Date addedDate;
//    private boolean live;
//    private boolean stock;

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImageName(productDto.getProductImageName());
        var savedProduct = productRepository.save(product);

        return mapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto get(String productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id"));
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equals("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        var page = productRepository.findAll(pageable);

        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equals("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        var page = productRepository.findByLiveTrue(pageable);

        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> seaerchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equals("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        var page = productRepository.findByTitleContaining(subTitle, pageable);

        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public ProductDto createWithCategory(ProductDto productDto, String categoryId) {
        //fetch the category
        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given Id"));
        Product product = mapper.map(productDto, Product.class);

        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setAddedDate(new Date());
        product.setCategory(category);
        var saveProduct = productRepository.save(product);
        return mapper.map(saveProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateCategory(String productId, String categoryId) {
        //fetch product
        var product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id"));

        //fetch category
        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given Id"));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, ProductDto.class);
    }


    //get all product of given category
    public PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber,int  pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equals("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);


        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given Id"));
        Page<Product> page = productRepository.findByCategory(category,pageable);

        return Helper.getPageableResponse(page, ProductDto.class);
    }
}
