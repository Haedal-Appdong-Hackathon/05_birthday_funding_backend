package com.just.birthdayFunding.dto.gifticon.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GifticonSaveReq {
    private String category;
    private String name;
    private int price;
    private String imageUrl;
    private String brand;


}
