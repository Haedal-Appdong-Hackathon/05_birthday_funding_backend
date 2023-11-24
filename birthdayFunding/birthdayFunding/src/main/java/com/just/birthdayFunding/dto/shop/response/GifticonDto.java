package com.just.birthdayFunding.dto.shop.response;

import com.just.birthdayFunding.domain.gifticon.entity.Gifticon;
import com.just.birthdayFunding.domain.gifticon.entity.GifticonCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GifticonDto {
    private GifticonCategory category;
    private String name;
    private Integer price;
    private String imageUrl;
    private String brand;

    public Gifticon toEntity() {
        return Gifticon.builder()
                .name(name)
                .brand(brand)
                .imageUrl(imageUrl)
                .price(price)
                .category(category)
                .build();
    }
}
