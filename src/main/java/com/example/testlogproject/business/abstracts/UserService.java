package com.example.testlogproject.business.abstracts;

import com.example.testlogproject.business.dtos.UserDto;
import com.example.testlogproject.business.requests.create.CreateUserRequest;
import com.example.testlogproject.business.requests.update.UpdateUserRequest;
import com.example.testlogproject.core.utilities.results.DataResult;
import com.example.testlogproject.core.utilities.results.Result;
import com.example.testlogproject.entities.User;

import java.util.List;

public interface UserService {
    DataResult<List<UserDto>> list(String transactionId);
    DataResult<List<UserDto>> getAll();
    Result addUser(CreateUserRequest createUserRequest);
    Result deleteUserById(Integer id);
    Result updateUser(UpdateUserRequest updateUserRequest);
}
