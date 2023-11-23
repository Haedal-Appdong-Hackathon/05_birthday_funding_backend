package com.just.birthdayFunding.dto.funding.response;

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
    private Long progress;
    private UserSummaryDto writer;
}
