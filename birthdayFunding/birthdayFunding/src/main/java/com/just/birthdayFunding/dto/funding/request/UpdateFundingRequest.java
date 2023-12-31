package com.just.birthdayFunding.dto.funding.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateFundingRequest {
    @NotBlank(message = "제목은 빈 문자열이 될 수 없습니다.")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotBlank(message = "내용은 빈 문자열이 될 수 없습니다.")
    private String content;

    @NotNull(message = "희망 기프리콘 리스트는 null이 될 수 없습니다.")
    @Size(min = 1, message = "희망 기프리콘 리스트는 최소 1개 이상이어야 합니다.")
    private List<Long> wishListIds;
}
