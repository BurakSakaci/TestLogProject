package com.example.testlogproject.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull
    @Positive
    private Integer userId;

    @NotNull
    private String userFirstName;

    @NotNull
    private String userLastName;
}
