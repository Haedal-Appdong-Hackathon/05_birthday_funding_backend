package com.just.birthdayFunding.domain.friendship;

import com.just.birthdayFunding.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "friendship")
public class Friendship {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany
    @JoinColumn(name = "user")
    @Column(name = "user_id_1")
    private User user1;

    @OneToMany
    @JoinColumn(name = "user")
    @Column(name = "user_id_2")
    private User user2;

}
