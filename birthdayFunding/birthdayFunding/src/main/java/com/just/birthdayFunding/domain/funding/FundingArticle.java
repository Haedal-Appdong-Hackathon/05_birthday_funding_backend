package com.just.birthdayFunding.domain.funding;

import com.just.birthdayFunding.domain.common.BaseTimeEntity;
import com.just.birthdayFunding.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class FundingArticle extends BaseTimeEntity {
    @Id
    @Column(name = "funding_article_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate startDate;

    private LocalDate finishDate;

}