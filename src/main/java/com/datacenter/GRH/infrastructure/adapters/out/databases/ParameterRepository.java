package com.datacenter.GRH.infrastructure.adapters.out.databases;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.GRH.domain.models.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
}
