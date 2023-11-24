package com.just.birthdayFunding.domain.funding;

import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.gifticon.GifticonCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingArticleRepostiory extends JpaRepository<FundingArticle, Long> {

    Page<FundingArticle> findAll(Pageable pageable);
}
