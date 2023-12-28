package com.programing.movie.user.dto;

import com.programing.movie.user.model.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String firstname;
    private String lastname;

    @Email
    @NotBlank
    @NotNull
    private String email;

    private String password;
    private List<UUID> profileIds;
    private List<Role> roles;
    private Date dateOfBirth;

    @AssertFalse
    private Boolean verification;
}
