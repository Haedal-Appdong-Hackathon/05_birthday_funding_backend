package com.just.birthdayFunding.domain.funding;

import com.just.birthdayFunding.domain.common.BaseTimeEntity;
import com.just.birthdayFunding.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "funding_article")
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


    @OneToMany(mappedBy = "fundingArticle",fetch = FetchType.LAZY)
    private List<ArticleGifticon> articleGifticonList = new ArrayList<>();


    @OneToMany(mappedBy = "fundingArticle",fetch = FetchType.LAZY)
    private List<FundingParticipant> fundingParticipantList = new ArrayList<>();

    @Builder
    public FundingArticle(User user, String title, String content, LocalDate startDate, LocalDate finishDate, List<ArticleGifticon> articleGifticonList){
        this.user = user;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.articleGifticonList = articleGifticonList;

    }

    public void addArticleGifticonList(List<ArticleGifticon> articleGifticonList){
        this.articleGifticonList = articleGifticonList;
        this.articleGifticonList.forEach(articleGifticon -> articleGifticon.setFundingArticle(this));
    }

    public void update(String title, String content, LocalDate startDate, LocalDate finishDate, List<ArticleGifticon> articleGifticonList){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.articleGifticonList = articleGifticonList;
    }

    public void addFundingParticipant(FundingParticipant fundingParticipant){
        this.fundingParticipantList.add(fundingParticipant);
    }

}
