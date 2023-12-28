package com.programing.movie.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmations_user")
@Entity
public class ConfirmationEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationEmail(User user) {
        this.user = user;
        this.createDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

}
