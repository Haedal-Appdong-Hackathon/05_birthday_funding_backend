package com.just.birthdayFunding.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "nickname", nullable = false,  unique = true)
    private String nickname;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "image_id")
    private UserImage userImage;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
