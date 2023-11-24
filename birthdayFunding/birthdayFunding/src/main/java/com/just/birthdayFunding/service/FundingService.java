package com.just.birthdayFunding.service;

import com.just.birthdayFunding.domain.friendship.Friendship;
import com.just.birthdayFunding.domain.friendship.FriendshipRepository;
import com.just.birthdayFunding.domain.funding.*;
import com.just.birthdayFunding.domain.gifticon.Gifticon;
import com.just.birthdayFunding.domain.gifticon.GifticonRepository;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.domain.user.UserGifticonRepositroy;
import com.just.birthdayFunding.domain.user.UserRepository;
import com.just.birthdayFunding.dto.common.response.PagingResponse;
import com.just.birthdayFunding.dto.funding.request.CloseFundingRequest;
import com.just.birthdayFunding.dto.funding.request.CreateFundingRequest;
import com.just.birthdayFunding.dto.funding.request.JoinFundingRequest;
import com.just.birthdayFunding.dto.funding.request.UpdateFundingRequest;
import com.just.birthdayFunding.dto.funding.response.CloseFundingResponse;
import com.just.birthdayFunding.dto.funding.response.FundingDetailResponse;
import com.just.birthdayFunding.dto.funding.response.FundingSummaryDto;
import com.just.birthdayFunding.dto.shop.response.GifticonDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final UserGifticonRepositroy userGifticonRepositroy;

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
        FundingArticle artcle = fundingArticleRepostiory.findById(fid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 펀딩입니다.")
        );
        if(!Objects.equals(artcle.getUser().getId(), userId)) throw new IllegalArgumentException("펀딩 작성자가 아닙니다.");

        artcle.update(dto.getTitle(), dto.getContent(), dto.getStartDate(), dto.getEndDate(), dto.getWishListIds().stream().map(gid -> {
            Gifticon gifticon = gifticonRepository.findById(gid).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 기프리콘을 요청하였습니다.")
            );
            ArticleGifticon articleGifticon = ArticleGifticon.builder()
                    .gifticon(gifticon)
                    .build();
            return articleGifticon;
        }).toList());

    }
    @Transactional
    public void joinFunding(Long fid, JoinFundingRequest dto, Long userId) {
        FundingArticle artcle = fundingArticleRepostiory.findById(fid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 펀딩입니다.")
        );

        User funder = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        //사용자의 포인트에서 구입한 기프티콘만큼 포인트 차감
        Integer userPoint = funder.getPoint();
        List<Gifticon> gifticons = dto.getGifticonIdList().stream().map(gid -> gifticonRepository.findById(gid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 기프리콘을 요청하였습니다.")
        )).toList();
        Integer fundingPrice = gifticons.stream().mapToInt(Gifticon::getPrice).sum();
        if(userPoint < fundingPrice) throw new IllegalArgumentException("포인트가 부족합니다.");
        funder.setPoint(userPoint - fundingPrice);

        //펀딩 게시글에 펀딩참여 정보 추가
        List<Gifticon> gifticonList = dto.getGifticonIdList().stream().map(gid -> gifticonRepository.findById(gid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 기프리콘을 요청하였습니다.")
        )).toList();

        if(artcle.getFundingParticipantList().contains(funder)){
            FundingParticipant fundingParticipant = artcle.getFundingParticipantList().stream().filter(fundingParticipant1 -> fundingParticipant1.getUser().getId().equals(userId)).findFirst().orElseThrow();
            fundingParticipant.addGifticonList(gifticonList);


        }else{
            FundingParticipant fundingParticipant = FundingParticipant.builder()
                    .fundingArticle(artcle)
                    .user(funder)
                    .gifticonList(gifticonList)
                    .build();
        }


    }
    @Transactional
    public CloseFundingResponse closeFunding(Long fid, CloseFundingRequest dto, Long userId) {
        FundingArticle artcle = fundingArticleRepostiory.findById(fid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 펀딩입니다.")
        );
        if(!Objects.equals(artcle.getUser().getId(), userId)) throw new IllegalArgumentException("펀딩 작성자가 아닙니다.");

//        List<FundingParticipant> fundingParticipantList = artcle.getFundingParticipantList();
//        List<ArticleGifticon> articleGifticonList = artcle.getArticleGifticonList();
        return null;
    }

    public FundingDetailResponse getFundingDetail(Long fid, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        FundingArticle fundingArticle = fundingArticleRepostiory.findById(fid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 펀딩입니다.")
        );
        List<GifticonDto> wishList = fundingArticle.getArticleGifticonList().stream().map(articleGifticon -> {
            Gifticon gifticon = articleGifticon.getGifticon();
            return GifticonDto.fromEntity(gifticon);
        }).toList();

        return FundingDetailResponse.fromEntity(fundingArticle, user, wishList);
    }
}
