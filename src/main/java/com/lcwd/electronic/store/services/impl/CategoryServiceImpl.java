package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.entities.Category;
import com.lcwd.electronic.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.CategoryRepositry;
import com.lcwd.electronic.store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepositry categoryRepositry;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        //create random id

        String random= UUID.randomUUID().toString();
        categoryDto.setCategoryId(random);
        Category category = mapper.map(categoryDto, Category.class);
        Category save = categoryRepositry.save(category);
        return mapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        //update details
        category.setTitle(categoryDto.getTitle());
        category.setDecription(categoryDto.getDecription());
        category.setCoverImage(categoryDto.getCoverImage());

        Category updatedCategory = categoryRepositry.save(category);

        return mapper.map(updatedCategory, CategoryDto.class);


    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepositry.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Category> page = categoryRepositry.findAll(pageable);

        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);

        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return mapper.map(category,CategoryDto.class);
    }
}
