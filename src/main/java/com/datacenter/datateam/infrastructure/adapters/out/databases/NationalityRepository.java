package com.datacenter.datateam.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.Nationality;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
}

