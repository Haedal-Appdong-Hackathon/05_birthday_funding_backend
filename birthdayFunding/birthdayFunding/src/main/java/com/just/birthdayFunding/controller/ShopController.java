package com.just.birthdayFunding.controller;

import com.just.birthdayFunding.core.anotation.TokenUserId;
import com.just.birthdayFunding.domain.gifticon.GifticonCategory;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.shop.response.BuyGifticonResponse;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import com.just.birthdayFunding.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public PagingResponse<GifticonDto> getGifticonList(@RequestParam int page, @RequestParam GifticonCategory category) {
        return shopService.getGifticonList(page, category);
    }

    @PostMapping("/{gid}/buy")
    public BuyGifticonResponse buyGifticon(@PathVariable Long gid, @TokenUserId Long userId) {
        return shopService.buyGifticon(gid, userId);
    }
}
