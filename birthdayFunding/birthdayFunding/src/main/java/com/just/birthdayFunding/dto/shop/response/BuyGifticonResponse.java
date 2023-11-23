package com.just.birthdayFunding.dto.shop.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class BuyGifticonResponse {
    private Long id;
    private String name;
    private String brand;
    private Long price;
    private String imageUrl;
    private LocalDate expirationDate;
}
