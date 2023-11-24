package com.just.birthdayFunding.dto.funding.response;

import com.just.birthdayFunding.domain.funding.FundingArticle;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.dto.user.response.UserInfoResponse;
import com.just.birthdayFunding.dto.user.response.UserSummaryDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class FundingSummaryDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double progress;
    private UserSummaryDto writer;

    public static FundingSummaryDto fromEntity(FundingArticle article, User user) {
        int hope = article.getArticleGifticonList()
                .stream().mapToInt(articleGifticon -> articleGifticon.getGifticon().getPrice()).sum();
        int current = article.getFundingParticipantList().stream().mapToInt(e->{
            return e.getGifticonList().stream().mapToInt(Gifticon::getPrice).sum();
        }).sum();
        Double progress = (double)current/hope;
        UserSummaryDto writer = UserSummaryDto.fromEntity(user);
        return FundingSummaryDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .startDate(article.getStartDate())
                .endDate(article.getFinishDate())
                .progress(progress)
                .writer(writer)
                .build();
    }
}
