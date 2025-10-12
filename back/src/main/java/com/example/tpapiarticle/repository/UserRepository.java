package com.example.tpapiarticle.repository;

import com.example.tpapiarticle.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String username);
}
