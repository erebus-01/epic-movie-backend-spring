package com.programing.movie.user.dto;

import com.programing.movie.user.model.Role;
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
public class UserResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private List<UUID> profileIds;
    private List<Role> roles;
    private Date dateOfBirth;
    private Boolean verification;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
