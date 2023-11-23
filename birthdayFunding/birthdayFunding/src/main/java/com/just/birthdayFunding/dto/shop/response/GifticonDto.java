package com.just.birthdayFunding.dto.shop.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GifticonDto {
    private Long id;
    private String name;
    private String brand;
    private Long price;
    private String imageUrl;
}
