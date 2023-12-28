package com.programing.movie.admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmations")
@Entity
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @OneToOne(targetEntity = Admin.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "admin_id")
    private Admin admin;

    public Confirmation(Admin admin) {
        this.admin = admin;
        this.createDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

}
