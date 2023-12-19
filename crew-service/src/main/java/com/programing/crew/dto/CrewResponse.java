package com.programing.crew.dto;

import com.programing.crew.model.Gender;
import com.programing.crew.model.TypeCrew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrewResponse {

    private UUID id;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String nation;
    private Gender gender;
    private String description;
    private TypeCrew typeCrew;
    private String image;
    private List<UUID> movies;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
