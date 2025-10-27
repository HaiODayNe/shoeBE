package com.Shoe.service;

import com.Shoe.dto.request.customerRequest.user.UserRequest;
import com.Shoe.model.user.User;

public interface UserService {
    //1. quản lý tài khoản người dùng (đăng ký, đăng nhập, đổi mật khẩu, quên mk)
    //2. phân quyền
    //3. xác thực
    //4. liên kết với thông tin cá nhân
    User createUser(UserRequest userRequest);
    Boolean login(UserRequest userRequest);
    User updateUserPassword(String phoneNumber, String password);
    User forgotPassword(String phoneNumber);
}
