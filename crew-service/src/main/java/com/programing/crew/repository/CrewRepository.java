package com.programing.crew.repository;

import com.programing.crew.dto.CrewResponse;
import com.programing.crew.model.Crew;
import com.programing.crew.model.TypeCrew;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CrewRepository extends JpaRepository<Crew, UUID> {

    List<Crew> findByTypeCrew(TypeCrew typeCrew, Sort sort);



}
