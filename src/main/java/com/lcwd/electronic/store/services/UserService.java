package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto userDto);
    //update

    UserDto updateUser(UserDto userDto,String userid);

    //delete

    void deleteUser(String userId);

    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get user by id
    UserDto getUserById(String userId);

    //get Single User by email

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);




}
