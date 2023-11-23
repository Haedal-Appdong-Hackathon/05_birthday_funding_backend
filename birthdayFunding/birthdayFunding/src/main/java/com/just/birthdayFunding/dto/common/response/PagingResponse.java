package com.just.birthdayFunding.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PagingResponse<T> {
    private Boolean hasNext;
    private List<T> data;
}
