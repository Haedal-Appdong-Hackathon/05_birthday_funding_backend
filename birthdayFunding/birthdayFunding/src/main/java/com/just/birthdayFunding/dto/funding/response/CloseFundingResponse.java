package com.just.birthdayFunding.dto.funding.response;

import com.just.birthdayFunding.dto.user.response.UserGifticonDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CloseFundingResponse {
    private Long point;
    private List<UserGifticonDto> gifticonList;
}
