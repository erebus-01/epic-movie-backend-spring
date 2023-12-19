package com.programing.series.repository;

import com.programing.series.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
}
