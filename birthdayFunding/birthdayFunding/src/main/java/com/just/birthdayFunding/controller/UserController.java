//package com.just.birthdayFunding.controller;
//
//import com.just.birthdayFunding.core.anotation.TokenUserId;
//import com.just.birthdayFunding.dto.user.response.UserInfoResponse;
//import com.just.birthdayFunding.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/user")
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping("/{userId}")
//    public UserInfoResponse getUserInfo(@TokenUserId Long userId) {
//        return userService.getUserById(userId).orElse(null);
//    }
//
//    @PutMapping()
//
//
//
//}
