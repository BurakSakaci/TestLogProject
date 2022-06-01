package com.example.testlogproject.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;

    private String transactionalId;

    private String userFirstName;

    private String userLastName;


}
