package com.genspark.restapis.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;


import java.util.List;

public interface UserDetailsRestRepository extends PagingAndSortingRepository<UserDetails, Long> {
    List<UserDetails> findByRole(String role);
}
