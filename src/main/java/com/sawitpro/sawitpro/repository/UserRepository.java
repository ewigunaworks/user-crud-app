package com.sawitpro.sawitpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sawitpro.sawitpro.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByPhoneNumber(String phoneNumber);
  Boolean existsByUsername(String username);
}
