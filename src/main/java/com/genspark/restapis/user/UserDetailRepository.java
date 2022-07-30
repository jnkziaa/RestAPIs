package com.genspark.restapis.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
    List<UserDetails> findByRole(String admin);
}
