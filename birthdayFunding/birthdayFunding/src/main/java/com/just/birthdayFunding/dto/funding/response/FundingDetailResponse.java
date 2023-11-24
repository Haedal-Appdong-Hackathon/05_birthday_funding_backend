package com.just.birthdayFunding.dto.funding.response;

import com.just.birthdayFunding.domain.funding.FundingArticle;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import com.just.birthdayFunding.dto.user.response.UserSummaryDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class FundingDetailResponse {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double progress;
    private UserSummaryDto writer;

    private String content;
    private Integer currentMoney;
    private List<GifticonDto> wishList;

    public static FundingDetailResponse fromEntity(FundingArticle article, User writer, List<Gifticon> wishList) {
        UserSummaryDto userSummaryDto = UserSummaryDto.fromEntity(writer);
        int hope = article.getArticleGifticonList()
                .stream().mapToInt(articleGifticon -> articleGifticon.getGifticon().getPrice()).sum();
        int current = article.getFundingParticipantList().stream().mapToInt(e->{
            return e.getGifticonList().stream().mapToInt(Gifticon::getPrice).sum();
        }).sum();

        Double progress = (double)current/hope * 100;
        Double roundedValue = Math.round(progress * 100.0) / 100.0;
        return FundingDetailResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .startDate(article.getStartDate())
                .endDate(article.getFinishDate())
                .progress(roundedValue)
                .writer(userSummaryDto)
                .content(article.getContent())
                .currentMoney(current)
                .wishList(wishList.stream().map(GifticonDto::fromEntity).toList())
                .build();
    }
}
