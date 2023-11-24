package com.just.birthdayFunding.domain.gifticon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gifticon")
public class Gifticon {
    @Id
    @Column(name = "gifticon_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column
    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GifticonCategory category;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public Gifticon(Integer price, String name, String brand, GifticonCategory category, String imageUrl) {
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.imageUrl = imageUrl;
    }

}
