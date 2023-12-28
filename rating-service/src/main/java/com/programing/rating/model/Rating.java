package com.programing.rating.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userProfile;
    @Enumerated(EnumType.STRING)
    private RatingLevel ratingLevel;
    private UUID movie;

    @CreationTimestamp
    private LocalDateTime createAt;

}
