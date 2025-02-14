package com.datacenter.datateam.infrastructure.adapters.out.databases;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
    List<Parameter> findByCategory(String category);
}
