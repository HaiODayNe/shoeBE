package com.Shoe.service.Impl;

import com.Shoe.converter.UserDTOConverter;
import com.Shoe.dto.request.customerRequest.user.UserRequest;
import com.Shoe.model.user.User;
import com.Shoe.repository.user.UserRepository;
import com.Shoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
//        Optional<User> userFound=userRepository.findByPhoneNumber(userRequest.getPhoneNumber());
//        if(userFound.isEmpty()){
//            User newUser= UserDTOConverter.ConvertToEntity(userRequest);
//            return userRepository.save(newUser);
//        }else{
//            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "Khong tim thay so dien thoai");
//        }
        return null;
    }
    @Override
    public Boolean login(UserRequest userRequest) {
//        Optional<User> userFound=userRepository.findByPhoneNumber(userRequest.getPhoneNumber());
//        if(userFound.isEmpty()){
//            return  false;
//        }
//        String passwordRequest=userRequest.getPassword();
//        String password=userFound.get().getPassword();
//        return  password.equals(passwordRequest);
        return null;
    }

    @Override
    public User updateUserPassword(String phoneNumber, String password) {
        return null;
    }

    @Override
    public User forgotPassword(String phoneNumber) {
        return null;
    }
}
