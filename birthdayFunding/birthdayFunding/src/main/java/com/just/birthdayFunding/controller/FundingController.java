package com.just.birthdayFunding.controller;

import com.just.birthdayFunding.core.anotation.TokenUserId;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.funding.request.CloseFundingRequest;
import com.just.birthdayFunding.dto.funding.request.CreateFundingRequest;
import com.just.birthdayFunding.dto.funding.request.JoinFundingRequest;
import com.just.birthdayFunding.dto.funding.request.UpdateFundingRequest;
import com.just.birthdayFunding.dto.funding.response.CloseFundingResponse;
import com.just.birthdayFunding.dto.funding.response.FundingDetailResponse;
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
    public PagingResponse<FundingSummaryDto> getFundingList(@RequestParam int page, @TokenUserId Long userId) {
        return fundingService.getFundingList(page, userId);
    }

    @PostMapping
    public Long createFunding(@RequestBody @Valid CreateFundingRequest dto, @TokenUserId Long userId) {
        return fundingService.createFunding(dto, userId);
    }

    @GetMapping("/{fid}")
    public FundingDetailResponse getFundingDetail(@PathVariable Long fid, @TokenUserId Long userId) {
        return fundingService.getFundingDetail(fid, userId);
    }

    @PutMapping("/{fid}")
    public void updateFunding(@PathVariable Long fid, @RequestBody UpdateFundingRequest dto, @TokenUserId Long userId) {
        fundingService.updateFunding(fid, dto, userId);
    }

    @PostMapping("/{fid}/join")
    public void joinFunding(@PathVariable Long fid, @Valid @RequestBody JoinFundingRequest dto, @TokenUserId Long userId) {
        fundingService.joinFunding(fid, dto, userId);
    }

    @PostMapping("/{fid}/close")
    public CloseFundingResponse closeFunding(@PathVariable Long fid, @RequestBody @Valid CloseFundingRequest dto, @TokenUserId Long userId) {
        return fundingService.closeFunding(fid, dto, userId);
    }


}
