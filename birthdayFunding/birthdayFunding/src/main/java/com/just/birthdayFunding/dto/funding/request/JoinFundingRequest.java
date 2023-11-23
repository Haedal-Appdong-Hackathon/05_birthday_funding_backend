package com.just.birthdayFunding.dto.funding.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class JoinFundingRequest {
    @NotNull(message = "기프리콘 리스트는 null이 될 수 없습니다.")
    @Size(min = 1, message = "기프리콘 리스트는 최소 1개 이상이어야 합니다.")
    private List<Long> gifticonIdList;
}
