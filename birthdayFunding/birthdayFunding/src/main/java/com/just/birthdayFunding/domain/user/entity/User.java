package com.just.birthdayFunding.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    private Long id;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Long id, String nickname, String imagePath) {
        this.id = id;
        this.birthdate = null;
        this.nickname = nickname;
        this.point = 0;
        this.imagePath = imagePath;
        this.createdDate = LocalDate.now();
        this.role = Role.ROLE_USER;
    }

}
