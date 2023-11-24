package com.just.birthdayFunding.domain.user.dao;

import com.just.birthdayFunding.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
