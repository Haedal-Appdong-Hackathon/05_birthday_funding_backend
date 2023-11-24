package com.just.birthdayFunding.domain.funding;

import com.just.birthdayFunding.domain.common.BaseTimeEntity;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "funding_participant")
public class FundingParticipant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "funding_participant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany()
    private List<Gifticon> gifticonList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_article_id")
    private FundingArticle fundingArticle;

    @Builder
    public FundingParticipant(User user, List<Gifticon> gifticonList, FundingArticle fundingArticle){
        this.user = user;
        this.gifticonList = gifticonList;
        this.fundingArticle = fundingArticle;
    }

    public void addGifticonList(List<Gifticon> gifticonList){
        this.gifticonList.addAll(gifticonList);
    }

}
