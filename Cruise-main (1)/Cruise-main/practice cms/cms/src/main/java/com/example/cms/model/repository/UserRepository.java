package com.example.cms.model.repository;

import com.example.cms.model.entity.Survey;
import com.example.cms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    static Optional<User> findByUsername(String username);
}
