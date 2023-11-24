package com.just.birthdayFunding.dto.user.response;

import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.user.UserGifticon;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserGifticonDto {
    private Long id;
    private String name;
    private Integer price;
    private String imgUrl;
    private String brand;
    private LocalDate dateOfUse;
    private LocalDate expirationDate;

    public static UserGifticonDto from(UserGifticon userGifticon) {
        return UserGifticonDto.builder()
                .id(userGifticon.getId())
                .name(userGifticon.getGifticon().getName())
                .price(userGifticon.getGifticon().getPrice())
                .imgUrl(userGifticon.getGifticon().getImageUrl())
                .brand(userGifticon.getGifticon().getBrand())
                .dateOfUse(userGifticon.getUsedDate())
                .expirationDate(userGifticon.getExpirationDate())
                .build();
    }
}
