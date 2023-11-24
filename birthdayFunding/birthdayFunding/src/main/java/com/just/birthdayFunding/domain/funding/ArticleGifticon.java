package com.just.birthdayFunding.domain.funding;

import com.just.birthdayFunding.domain.gifticon.Gifticon;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "article_gifticon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    @Builder
    public ArticleGifticon(Gifticon gifticon){
        this.gifticon = gifticon;
    }


}
