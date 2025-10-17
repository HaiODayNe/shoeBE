package com.Shoe.service.Impl;

import com.Shoe.dto.request.customerRequest.user.UserRequest;
import com.Shoe.model.user.User;
import com.Shoe.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public Boolean signUp(UserRequest userRequest) {
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
