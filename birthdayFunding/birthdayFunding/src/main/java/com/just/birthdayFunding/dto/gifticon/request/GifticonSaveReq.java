package com.just.birthdayFunding.dto.gifticon.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GifticonSaveReq {
    private String category;
    private String name;
    private int price;
    private String imageUrl;
    private String brand;


}
