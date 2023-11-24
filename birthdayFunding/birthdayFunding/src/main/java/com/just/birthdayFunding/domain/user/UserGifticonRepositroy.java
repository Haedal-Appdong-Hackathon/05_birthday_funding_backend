package com.just.birthdayFunding.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGifticonRepositroy extends JpaRepository<UserGifticon, Long> {
    List<UserGifticon> findByUserId(Long userId);
}
