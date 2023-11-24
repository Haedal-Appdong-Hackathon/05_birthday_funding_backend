package com.just.birthdayFunding.service;

import com.just.birthdayFunding.domain.friendship.FriendshipRepository;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.gifticon.GifticonRepository;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.domain.user.UserGifticon;
import com.just.birthdayFunding.domain.user.UserGifticonRepositroy;
import com.just.birthdayFunding.domain.user.UserRepository;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import com.just.birthdayFunding.dto.user.response.UserGifticonDto;
import com.just.birthdayFunding.dto.user.response.UserInfoResponse;
import com.just.birthdayFunding.dto.user.response.UserSummaryDto;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final UserGifticonRepositroy userGifticonRepository;
    private final FriendshipRepository friendshipRepository;
    @Transactional
    public UserInfoResponse getUserInfoById(Long userId) {
        return UserInfoResponse.from(userRepository.findById(userId).orElse(null));
    }
    @Transactional
    public UserInfoResponse updateUserInfo(String nickname, Long userId) {
        User targetUser = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 유저입니다."));
        targetUser.setNickname(nickname);
        return UserInfoResponse.from(targetUser);
    }
    @Transactional
    public PagingResponse<UserGifticonDto> getUserGifticons(Long userId, int page) {
        Page<UserGifticon> userGifticonPage =  userGifticonRepository
                .findAllByUserId(userId, PageRequest.of(page, 20));

        return new PagingResponse<>(userGifticonPage.hasNext(),
                userGifticonPage.stream()
                        .map(UserGifticonDto::from)
                        .collect(Collectors.toList()));
    }

//    public UserGifticonDto getUserGifticon() {
//
//    }

//    public PagingResponse<UserSummaryDto> getFriends(Long userId) {
//
//    }
}
