package com.just.birthdayFunding.controller;

import com.just.birthdayFunding.core.anotation.TokenUserId;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.user.response.UserGifticonDto;
import com.just.birthdayFunding.dto.user.response.UserInfoResponse;
import com.just.birthdayFunding.dto.user.response.UserSummaryDto;
import com.just.birthdayFunding.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserInfoResponse getUserInfo(@TokenUserId Long userId) {
        return userService.getUserInfoById(userId);
    }

    @PatchMapping
    public UserInfoResponse updateUserInfo(@RequestBody UserInfoResponse userInfoResponse, @TokenUserId Long userId) {
        return userService.updateUserInfo(userInfoResponse.getNickname(), userId);
    }

    @GetMapping("/gifticon")
    public PagingResponse<UserGifticonDto> getUserGifticons(@RequestParam int page, @TokenUserId Long userId) {
        return userService.getUserGifticons(userId, page);
    }

//    @GetMapping("/gifticon/{gid}")
//    public UserGifticonDto getUserGifticon(@TokenUserId Long userId) {
//        return userService.getUserGifticon();
//    }
//
//    @GetMapping("/friend")
//    public PagingResponse<UserSummaryDto> getFriends(@TokenUserId Long userId) {
//        return userService.getFriends(userId);
//    }


}
