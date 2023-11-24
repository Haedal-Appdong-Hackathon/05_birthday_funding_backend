package com.just.birthdayFunding.controller;

import com.just.birthdayFunding.domain.user.UserRepository;
import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.dto.auth.response.LoginResponse;
import com.just.birthdayFunding.service.AuthService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;

    @GetMapping("/login/kakao")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestParam(name = "code") String code) {
        try {
            // URL에 포함된 code를 이용하여 액세스 토큰 발급
            LoginResponse loginResponse = authService.getKakaoTokens(code);
            // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
            HashMap<String, String> userInfo = (HashMap<String, String>) authService.getUserInfo(
                    loginResponse.getAccessToken());

            //유저 id받아옴 (DB에 이 id로 저장할 예정)
            Long id = Long.parseLong(userInfo.get("id"));

            User user = userRepository.findById(id).orElse(null);

            // 만일, DB에 해당 email을 가지는 유저가 없으면 회원가입 시키고 유저 식별자와 JWT 반환
            // 현재 카카오 유저의 전화번호를 받아올 권한이 없어서 테스트를 하지 못함.
            if (user == null) {
                userRepository.save(new User(id, userInfo.get("nickname"), userInfo.get("imagePath")));
            }
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
