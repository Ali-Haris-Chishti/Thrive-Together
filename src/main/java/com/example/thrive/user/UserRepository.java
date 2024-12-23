package com.example.thrive.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUsername(String username);

    @Query("SELECT user.userRole FROM UserModel user WHERE user.username = :username")
    UserRole findUserRoleByUsername(@Param("username") String username);
}
