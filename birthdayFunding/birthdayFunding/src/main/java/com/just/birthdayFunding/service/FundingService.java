package com.just.birthdayFunding.service;

import com.just.birthdayFunding.domain.friendship.Friendship;
import com.just.birthdayFunding.domain.friendship.FriendshipRepository;
import com.just.birthdayFunding.domain.funding.ArticleGifticon;
import com.just.birthdayFunding.domain.funding.ArticleGifticonRepostiory;
import com.just.birthdayFunding.domain.funding.FundingArticle;
import com.just.birthdayFunding.domain.funding.FundingArticleRepostiory;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.gifticon.GifticonRepository;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.domain.user.UserRepository;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.funding.request.CloseFundingRequest;
import com.just.birthdayFunding.dto.funding.request.CreateFundingRequest;
import com.just.birthdayFunding.dto.funding.request.UpdateFundingRequest;
import com.just.birthdayFunding.dto.funding.response.CloseFundingResponse;
import com.just.birthdayFunding.dto.funding.response.FundingSummaryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundingService {
    private final FundingArticleRepostiory fundingArticleRepostiory;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final ArticleGifticonRepostiory articleGifticonRepostiory;
    private final FriendshipRepository friendshipRepository;

    @Transactional
    public PagingResponse<FundingSummaryDto> getFundingList(int page,Long userId) {
        User writer = userRepository.findById(userId).orElseThrow();
        List<User> friends = friendshipRepository.findByUser1Id(userId).stream().map(Friendship::getUser2).toList();
        Page<FundingArticle> articlesPage = fundingArticleRepostiory.findAll(PageRequest.of(page, 20));

        return new PagingResponse<>(articlesPage.hasNext(),
                articlesPage.stream().map(article -> FundingSummaryDto.fromEntity(article, writer)).collect(Collectors.toList()));

    }
    @Transactional
    public Long createFunding(CreateFundingRequest dto, Long userId) {
        User writer = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        List<Gifticon> gifticons = dto.getWishListIds().stream().map(gid -> gifticonRepository.findById(gid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 기프리콘을 요청하였습니다.")
        )).toList();
        List<ArticleGifticon> articleGifticonList = gifticons.stream().map(gifticon -> {
            ArticleGifticon articleGifticon = ArticleGifticon.builder()
                    .gifticon(gifticon)
                    .build();
            return articleGifticon;
        }).toList();

        log.info("gifticons: {}", articleGifticonList);
        articleGifticonRepostiory.saveAll(articleGifticonList);


        FundingArticle created = FundingArticle.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .startDate(dto.getStartDate())
                .finishDate(dto.getEndDate())
                .user(writer)
                .articleGifticonList(articleGifticonList)
                .build();
        log.info("created: {}", created);
        fundingArticleRepostiory.save(created);
        created.addArticleGifticonList(articleGifticonList);
        return created.getId();
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
