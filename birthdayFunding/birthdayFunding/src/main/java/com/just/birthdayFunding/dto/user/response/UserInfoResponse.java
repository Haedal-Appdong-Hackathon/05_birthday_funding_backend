package com.just.birthdayFunding.dto.user.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserInfoResponse {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String nickname;
    private Long point;
    private String userImageUrl;
}
