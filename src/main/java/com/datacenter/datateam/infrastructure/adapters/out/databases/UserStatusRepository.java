package com.datacenter.datateam.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
}

