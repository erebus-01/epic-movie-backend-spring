package com.programing.movie.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrewMapper {


    private UUID id;

    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String nation;
    private Gender gender;
    private String description;
    private TypeCrew typeCrew;
    private String image;

}
