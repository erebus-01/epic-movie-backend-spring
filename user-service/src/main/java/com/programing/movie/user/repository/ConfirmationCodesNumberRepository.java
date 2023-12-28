package com.programing.movie.user.repository;

import com.programing.movie.user.model.ConfirmationCodesNumber;
import com.programing.movie.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConfirmationCodesNumberRepository extends JpaRepository<ConfirmationCodesNumber, UUID> {
    ConfirmationCodesNumber findByToken(String token);
    Optional<ConfirmationCodesNumber> findByUser(User user);
}
