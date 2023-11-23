package com.just.birthdayFunding.dto.funding.response;

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
    private Long progress;
    private UserSummaryDto writer;

    private String content;
    private Long currentMoney;
    private List<GifticonDto> wishList;
}
