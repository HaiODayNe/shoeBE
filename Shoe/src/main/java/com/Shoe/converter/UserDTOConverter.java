package com.Shoe.converter;

import com.Shoe.dto.request.customerRequest.user.UserRequest;
import com.Shoe.dto.response.customerResponse.UserResponse;
import com.Shoe.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {
    public static User ConvertToEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAddress(userRequest.getAddress());
        user.setPassword(userRequest.getPassword());
        user.setBirthday(userRequest.getBirthDate());
        user.setGender(userRequest.getGender());
        return user;
    }
    public static UserResponse ConvertToResponse(User  user){
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setAddress(user.getAddress());
        return userResponse;
    }
//    Optional<User> userOpt = userRepository.findByEmail(input.getEmail());
//
//        if (userOpt.isPresent()) {
//        return ResponseEntity.badRequest().body("Email already exists");
//    }
//
//    String uuid = UUID.randomUUID().toString();
//
//    User user = new User();
//        user.setUuid(uuid);
//        user.setEmail(input.getEmail());
//        user.setFullName(input.getFullName());
//        user.setHashedPassword(calculateSha256(input.getPassword() + uuid));
//    User userSaved = userRepository.save(user);
//    Map<String, Object> claimMap = new HashMap<>();
//        claimMap.put("id", userSaved.getId());
//        claimMap.put("uuid", userSaved.getUuid());
//        claimMap.put("email", userSaved.getEmail());
//        claimMap.put("fullName", userSaved.getFullName());
//
//    String token = jwtUtils.generate(user.getEmail(), claimMap);
//        return ResponseEntity.ok(token);
}
