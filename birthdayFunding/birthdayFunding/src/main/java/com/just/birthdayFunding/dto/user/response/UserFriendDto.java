package com.just.birthdayFunding.dto.user.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserFriendDto {
    private Long id;
    private LocalDate birthday;
    private String nickname;
    private String userImageUrl;
}
