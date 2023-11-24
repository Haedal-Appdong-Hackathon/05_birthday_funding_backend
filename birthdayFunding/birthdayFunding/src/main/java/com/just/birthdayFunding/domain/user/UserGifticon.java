package com.just.birthdayFunding.domain.user;

import jakarta.persistence.*;
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

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "used_date")
    private LocalDate usedDate;

    public void setGifticonOwner(User user){
        this.user = user;
        user.getUserGifticonList().add(this);
    }
}
