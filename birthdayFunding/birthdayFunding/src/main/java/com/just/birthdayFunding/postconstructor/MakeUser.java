package com.just.birthdayFunding.postconstructor;

import com.just.birthdayFunding.domain.user.User;
import com.just.birthdayFunding.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component(value = "makeUser")
@RequiredArgsConstructor
@Slf4j
public class MakeUser {
    public final UserRepository userRepository;
    public final EntityManager em;

    @PostConstruct
    public void init(){
        log.info("MakeUser init");

        User user1 = User.builder()
                .id(1L).email("best@naver.com")
                .nickname("best")
                .imagePath("https://picsum.photos/200/200")
                .build();
        userRepository.save(user1);
    }
}
