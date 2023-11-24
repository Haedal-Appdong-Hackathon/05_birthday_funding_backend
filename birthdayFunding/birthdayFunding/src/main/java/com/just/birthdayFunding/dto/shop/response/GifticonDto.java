package com.just.birthdayFunding.dto.shop.response;

import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.gifticon.GifticonCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    public static GifticonDto fromEntity(Gifticon gifticon) {
        return GifticonDto.builder()
                .name(gifticon.getName())
                .brand(gifticon.getBrand())
                .imageUrl(gifticon.getImageUrl())
                .price(gifticon.getPrice())
                .category(gifticon.getCategory())
                .build();
    }
}
