package com.lcwd.electronic.store.dtos;


import com.lcwd.electronic.store.entities.Order;
import com.lcwd.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private String userId;

    @Size(min = 3, max = 15, message = "Invalid Name !!")
    private String name;

    //@Email(message = "Invalid User Email !!")
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,5})$",message = "Invalid User Email !!")
    @NotBlank(message = "email is required !!")
    private String email;

    @NotBlank(message = "password is required !!")
    private String password;

    @Size(min = 4, max = 6 ,message = "Invalid gender !!")
    private String gender;

    @NotBlank(message = "Write something about Yourself")
    private String about;


   @ImageNameValid(message = "Image not valid")
    private String imageName;

    private List<Order> orders=new ArrayList<>();
}
