package com.just.birthdayFunding.dto.user.response;

import com.just.birthdayFunding.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserInfoResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private String nickname;
    private Integer point;
    private String userImageUrl;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .birthdate(user.getBirthdate())
                .point(user.getPoint())
                .userImageUrl(user.getImagePath())
                .build();
    }
}
