package com.just.birthdayFunding.service;

import com.just.birthdayFunding.domain.gifticon.GifticonRepository;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.user.UserRepository;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.shop.response.BuyGifticonResponse;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final GifticonRepository gifticonRepository;
    private final UserRepository userRepository;
    @Transactional
    public PagingResponse<GifticonDto> getGifticonList(int page, String category) {
        return null;
    }
    @Transactional
    public BuyGifticonResponse buyGifticon(Long gid, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Gifticon gifticon = gifticonRepository.findById(gid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기프티콘입니다."));
        return null;

    }
}
