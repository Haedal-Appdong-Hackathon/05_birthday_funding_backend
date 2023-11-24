package com.just.birthdayFunding.controller;

import com.just.birthdayFunding.core.anotation.TokenUserId;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.funding.request.CloseFundingRequest;
import com.just.birthdayFunding.dto.funding.request.CreateFundingRequest;
import com.just.birthdayFunding.dto.funding.request.UpdateFundingRequest;
import com.just.birthdayFunding.dto.funding.response.CloseFundingResponse;
import com.just.birthdayFunding.dto.funding.response.FundingSummaryDto;
import com.just.birthdayFunding.service.FundingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funding")
public class FundingController {
    private final FundingService fundingService;

    @GetMapping
    public PagingResponse<FundingSummaryDto> getFundingList() {
        return null;
    }

    @PostMapping
    public Long createFunding(@RequestBody @Valid CreateFundingRequest dto, @TokenUserId Long userId) {
        return null;
    }

    @PutMapping("/{fid}")
    public void updateFunding(@PathVariable Long fid, @RequestBody UpdateFundingRequest dto, @TokenUserId Long userId) {

    }

    @PostMapping("/{fid}/join")
    public void joinFunding(@PathVariable Long fid, @TokenUserId Long userId) {

    }

    @PostMapping("/{fid}/close")
    public CloseFundingResponse closeFunding(@PathVariable Long fid, @RequestBody @Valid CloseFundingRequest dto, @TokenUserId Long userId) {
        return null;
    }


}
