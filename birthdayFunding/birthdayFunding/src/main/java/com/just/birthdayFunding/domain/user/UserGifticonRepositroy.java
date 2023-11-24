package com.just.birthdayFunding.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGifticonRepositroy extends JpaRepository<UserGifticon, Long> {
    Page<UserGifticon> findAllByUserId(Long userId, PageRequest of);
}
