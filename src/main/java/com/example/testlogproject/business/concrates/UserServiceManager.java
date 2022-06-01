package com.example.testlogproject.business.concrates;

import com.example.testlogproject.business.abstracts.UserService;
import com.example.testlogproject.business.constants.testLog;
import com.example.testlogproject.business.dtos.UserDto;
import com.example.testlogproject.business.requests.create.CreateUserRequest;
import com.example.testlogproject.business.requests.update.UpdateUserRequest;
import com.example.testlogproject.core.utilities.mapping.ModelMapperService;
import com.example.testlogproject.core.utilities.results.DataResult;
import com.example.testlogproject.core.utilities.results.Result;
import com.example.testlogproject.core.utilities.results.SuccessDataResult;
import com.example.testlogproject.core.utilities.results.SuccessResult;
import com.example.testlogproject.dataAccess.UserRepository;
import com.example.testlogproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceManager implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperService modelMapperService;



    @testLog
    @Override
    public DataResult<List<UserDto>> list(String transactionId) {
        List<UserDto> userDtos = userListMapper();
        if (transactionId.isEmpty()){
            return new SuccessDataResult<>(userDtos, "Veri Listelendi");

        }else {
            for (int i = 0; i < userDtos.size(); i++) {
                userDtos.get(i).setTransactionalId(transactionId);
            }
            return new SuccessDataResult<>(userDtos, "Veri TransactionId ile Listelendi");
        }
    }

    @testLog
    @Override
    public DataResult<List<UserDto>> getAll() {
        List<UserDto> userDtos = userListMapper();

        return new SuccessDataResult<>(userDtos, "Veri Listelendi");
    }

    @testLog
    @Override
    public Result addUser(CreateUserRequest createUserRequest) {
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userRepository.save(user);
        return new SuccessResult("Kullanıcı eklendi");
    }

    @testLog
    @Override
    public Result deleteUserById(Integer id) {
        this.userRepository.deleteById(id);
        return new SuccessResult("Kullanıcı silindi");
    }

    @testLog
    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {
        User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);
        return new SuccessResult("Kullanıcı güncellendi");
    }


    private List<UserDto> userListMapper(){
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.modelMapperService.forDto().map(user, UserDto.class))
                .collect(Collectors.toList());

        for (int i = 0; i < userDtos.size(); i++) {
            userDtos.get(i).setTransactionalId(randomIdGenerator());
        }
        return userDtos;
    }

    public static String randomIdGenerator(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
