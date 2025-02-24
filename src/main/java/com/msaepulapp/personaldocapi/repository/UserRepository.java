package com.msaepulapp.personaldocapi.repository;

import com.msaepulapp.personaldocapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findFirstByToken(String token);
}
