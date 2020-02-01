package com.dandi.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dandi.api.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

  public User findByName(String name);

}