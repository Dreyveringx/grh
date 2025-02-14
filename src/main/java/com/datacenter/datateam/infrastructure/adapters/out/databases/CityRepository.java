package com.datacenter.datateam.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}

