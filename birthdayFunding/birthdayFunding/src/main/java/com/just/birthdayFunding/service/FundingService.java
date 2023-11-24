package com.just.birthdayFunding.service;

import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.funding.request.CloseFundingRequest;
import com.just.birthdayFunding.dto.funding.request.CreateFundingRequest;
import com.just.birthdayFunding.dto.funding.request.UpdateFundingRequest;
import com.just.birthdayFunding.dto.funding.response.CloseFundingResponse;
import com.just.birthdayFunding.dto.funding.response.FundingSummaryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundingService {
    @Transactional
    public PagingResponse<FundingSummaryDto> getFundingList(Long userId) {
        return null;
    }
    @Transactional
    public Long createFunding(CreateFundingRequest dto, Long userId) {
        return null;
    }
    @Transactional
    public void updateFunding(Long fid, UpdateFundingRequest dto, Long userId) {

    }
    @Transactional
    public void joinFunding(Long fid, Long userId) {
    }
    @Transactional
    public CloseFundingResponse closeFunding(Long fid, CloseFundingRequest dto, Long userId) {
        return null;
    }
}
