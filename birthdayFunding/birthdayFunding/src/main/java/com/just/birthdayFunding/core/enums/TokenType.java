package com.just.birthdayFunding.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    // 엑세스토큰 -> 30분
    ACCESS_TOKEN(60 * 30L),
    // 리프레시 토큰 -> 1달
    REFRESH_TOKEN(60 * 60 * 24 * 30L);
    private final Long expireTime;
}
