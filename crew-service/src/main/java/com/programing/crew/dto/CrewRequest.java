package com.programing.crew.dto;

import com.programing.crew.model.Gender;
import com.programing.crew.model.TypeCrew;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrewRequest {

    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String nation;
    private Gender gender;
    private String description;
    private TypeCrew typeCrew;
    private String image;
    private List<UUID> movies;

}
