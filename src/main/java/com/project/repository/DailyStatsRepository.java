package com.project.repository;

import com.project.model.DailyStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStatsRepository extends CrudRepository<DailyStats, Long> {

}
