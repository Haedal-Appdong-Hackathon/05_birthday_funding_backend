package com.just.birthdayFunding.service;

import com.just.birthdayFunding.domain.gifticon.GifticonCategory;
import com.just.birthdayFunding.domain.gifticon.GifticonRepository;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.user.UserGifticon;
import com.just.birthdayFunding.domain.user.UserRepository;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.shop.response.BuyGifticonResponse;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final GifticonRepository gifticonRepository;
    private final UserRepository userRepository;
    @Transactional
    public PagingResponse<GifticonDto> getGifticonList(int page, GifticonCategory category) {
        Page<Gifticon> gifticonPage = gifticonRepository
                .findAllByCategory(category, PageRequest.of(page, 20));

        return new PagingResponse<>(gifticonPage.hasNext(),
                gifticonPage.stream().map(GifticonDto::fromEntity).collect(Collectors.toList()));
    }
    @Transactional
    public BuyGifticonResponse buyGifticon(Long gid, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Gifticon gifticon = gifticonRepository.findById(gid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기프티콘입니다."));
        UserGifticon userGifticon = UserGifticon.builder()
                .user(user)
                .gifticon(gifticon)
                .build();
        return BuyGifticonResponse.builder().id(userGifticon.getId())
                .name(gifticon.getName())
                .brand(gifticon.getBrand())
                .price(gifticon.getPrice())
                .imageUrl(gifticon.getImageUrl())
                .expirationDate(userGifticon.getExpirationDate())
                .build();

    }
}
