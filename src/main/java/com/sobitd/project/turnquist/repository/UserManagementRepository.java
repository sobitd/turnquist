package com.sobitd.project.turnquist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sobitd.project.turnquist.entity.UserAccount;

public interface UserManagementRepository extends
JpaRepository<UserAccount, Long> {
}
