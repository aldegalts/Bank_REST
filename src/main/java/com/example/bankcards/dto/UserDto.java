package com.example.bankcards.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String fullName;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private Set<String> roles;
}
