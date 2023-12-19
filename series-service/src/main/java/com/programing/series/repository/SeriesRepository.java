package com.programing.series.repository;

import com.programing.series.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Series, UUID> {
}
