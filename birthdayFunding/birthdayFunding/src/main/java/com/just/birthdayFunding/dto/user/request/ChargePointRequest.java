package com.just.birthdayFunding.dto.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;


@Getter
public class ChargePointRequest {
    @NotNull(message = "충전 포인트는 null이 될 수 없습니다.")
    @Positive(message = "충전 포인트는 0보다 커야 합니다.")
    private Integer point;
}
