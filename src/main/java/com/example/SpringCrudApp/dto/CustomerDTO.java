package com.example.SpringCrudApp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    @NotBlank(message = "name shouldn't be null or empty")
    private String name;

    @Size(min = 5, message = "code must contain 5 characters")
    private String address;
    @Email
    private String email;

}
