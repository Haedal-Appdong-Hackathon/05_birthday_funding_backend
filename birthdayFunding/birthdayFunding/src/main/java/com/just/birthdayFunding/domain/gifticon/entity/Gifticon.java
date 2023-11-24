package com.just.birthdayFunding.domain.gifticon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gifticon {
    @Id
    @Column(name = "gifticon_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer price;

    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GifticonCategory category;

    @Column(nullable = false)
    private String imagePath;

    @Builder
    public Gifticon(Integer price, String itemName, String brand, GifticonCategory category, String imagePath) {
        this.price = price;
        this.itemName = itemName;
        this.brand = brand;
        this.category = category;
        this.imagePath = imagePath;
    }

}
