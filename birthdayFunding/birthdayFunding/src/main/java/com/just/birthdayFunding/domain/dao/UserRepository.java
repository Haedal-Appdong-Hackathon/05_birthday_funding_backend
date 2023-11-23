package com.just.birthdayFunding.domain.dao;

import com.just.birthdayFunding.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
