package com.programing.crew.repository;

import com.programing.crew.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrewRepository extends JpaRepository<Crew, UUID> {
}
