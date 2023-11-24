package com.just.birthdayFunding.dto.user.response;

import com.just.birthdayFunding.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserSummaryDto {
    private Long id;
    private LocalDate birthday;
    private String nickname;
    private String userImageUrl;

    public static UserSummaryDto fromEntity(User user) {
        return UserSummaryDto.builder()
                .id(user.getId())
                .birthday(user.getBirthdate())
                .nickname(user.getNickname())
                .userImageUrl(user.getImagePath())
                .build();
    }
}
