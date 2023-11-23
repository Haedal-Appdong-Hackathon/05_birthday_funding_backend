package com.just.birthdayFunding.dto.user.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserGifticonDto {
    private Long id;
    private String name;
    private Long price;
    private String imgUrl;
    private String brand;
    private LocalDateTime dateOfUse;
    private LocalDateTime expirationDate;
}
