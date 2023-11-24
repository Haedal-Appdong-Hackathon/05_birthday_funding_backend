package com.just.birthdayFunding.domain.friendship;

import com.just.birthdayFunding.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "friendship")
public class Friendship {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_1")
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_2")
    private User user2;

}
