package com.miso.dao;

import com.miso.entity.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanDAO extends CrudRepository<Plan, Long> {
}
