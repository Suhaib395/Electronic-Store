package com.lcwd.electronic.store.helper;

import com.lcwd.electronic.store.dtos.PageableResponse;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page ,Class<V> type){


        List<U> entity = page.getContent();

        List<V> userDtos = entity.stream().map((obj) ->  new ModelMapper().map(obj,type)).collect(Collectors.toList());

        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(userDtos);
        response.setLastPage(page.isLast());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        return response;
    }
}
