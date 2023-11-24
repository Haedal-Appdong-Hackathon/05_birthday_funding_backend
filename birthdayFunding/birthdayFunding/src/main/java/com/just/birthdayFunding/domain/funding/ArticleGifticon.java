package com.just.birthdayFunding.domain.funding;

import com.just.birthdayFunding.domain.gifticon.Gifticon;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "article_gifticon")
public class ArticleGifticon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_gifticon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_article_id")
    private FundingArticle fundingArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gifticon_id")
    private Gifticon gifticon;


    public void setFundingArticle(FundingArticle fundingArticle){
        this.fundingArticle = fundingArticle;
        fundingArticle.getArticleGifticonList().add(this);
    }
}
