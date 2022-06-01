package com.example.testlogproject.api.controller;

import com.example.testlogproject.business.abstracts.UserService;
import com.example.testlogproject.business.constants.testLog;
import com.example.testlogproject.business.dtos.UserDto;
import com.example.testlogproject.business.requests.create.CreateUserRequest;
import com.example.testlogproject.business.requests.update.UpdateUserRequest;
import com.example.testlogproject.core.utilities.results.DataResult;
import com.example.testlogproject.core.utilities.results.Result;
import com.example.testlogproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public DataResult<List<UserDto>> list(String transactionId){
        return this.userService.list(transactionId);
    }

    @GetMapping("/getAll")
    public DataResult<List<UserDto>> getAll(){
        return this.userService.getAll();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody CreateUserRequest createUserRequest){
        return this.userService.addUser(createUserRequest);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public Result deleteUserById(@PathVariable Integer id){
        return this.userService.deleteUserById(id);
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return this.userService.updateUser(updateUserRequest);
    }

}
