package com.programing.movie.user.repository;

import com.programing.movie.user.model.ConfirmationEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfirmationEmailRepository extends JpaRepository<ConfirmationEmail, UUID> {
    ConfirmationEmail findByToken(String token);
}
