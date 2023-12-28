package com.programing.movie.admin.repository;

import com.programing.movie.admin.model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, UUID> {
    Confirmation findByToken(String token);
}
