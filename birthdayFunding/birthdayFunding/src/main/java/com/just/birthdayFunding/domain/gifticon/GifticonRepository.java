package com.just.birthdayFunding.domain.gifticon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GifticonRepository extends JpaRepository<Gifticon, Long> {
    Page<Gifticon> findAllByCategory(GifticonCategory category, Pageable pageable);

}
