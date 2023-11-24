package com.just.birthdayFunding.controller;

import com.just.birthdayFunding.domain.gifticon.dao.GifticonRepository;
import com.just.birthdayFunding.domain.gifticon.entity.Gifticon;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gifticon")
public class GifticonController {
    private final GifticonRepository gifticonRepository;

    @Transactional
    @PostMapping("/save")
    public ResponseEntity<?> saveGifticons(@RequestBody List<GifticonDto> gifticonDtos) {
        List<Gifticon> gifticons = gifticonDtos.stream()
                .map(GifticonDto::toEntity)
                .collect(Collectors.toList());

        gifticonRepository.saveAll(gifticons);
        return new ResponseEntity(gifticons, HttpStatus.OK);
    }
}
