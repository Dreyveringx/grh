package com.datacenter.datateam.infrastructure.adapters.out.databases;

import java.lang.reflect.Parameter;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    List<Parameter> findByCategory(String category);
}
