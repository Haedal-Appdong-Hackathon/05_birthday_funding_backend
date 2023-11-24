package com.just.birthdayFunding.domain.user;

import com.just.birthdayFunding.domain.gifticon.Gifticon;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_gifticon")
public class UserGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gifticon_id")
    private Gifticon gifticon;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "used_date")
    private LocalDate usedDate;

    @Builder
    public UserGifticon(User user,Gifticon gifticon){
        this.user = user;
        this.gifticon = gifticon;
        this.expirationDate = LocalDate.now().plusYears(1);
    }

    public void buy(User user, Gifticon gifticon){
        this.user = user;
        this.gifticon = gifticon;
        user.getUserGifticonList().add(this);
    }
}
