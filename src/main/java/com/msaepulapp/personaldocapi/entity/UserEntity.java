package com.msaepulapp.personaldocapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String username;

    private String password;

    private String description;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

}
