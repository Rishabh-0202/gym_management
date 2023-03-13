package com.example.pokemon.repository;

import com.example.pokemon.model.User;
import org.apache.catalina.LifecycleState;
import org.apache.el.parser.BooleanNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    User findUserByUsername(String username);

    @Query("SELECT s from User s where s.roles = ?1")
    List<User> findByRoles(String roleName);

    Boolean existsByUsername(String username);
}
