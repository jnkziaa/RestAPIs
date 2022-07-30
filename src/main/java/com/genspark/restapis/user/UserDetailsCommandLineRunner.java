package com.genspark.restapis.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserDetailRepository repository;

    public UserDetailsCommandLineRunner(UserDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new UserDetails("John", "Admin"));
        repository.save(new UserDetails("Jake", "Admin"));
        repository.save(new UserDetails("Kevin", "Admin"));

        List<UserDetails> users = repository.findByRole("Admin");

        users.forEach(user -> logger.info(user.toString()));

    }
}
