package com.example.ComplainServiceApp.repositry;

import com.example.ComplainServiceApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepositry extends MongoRepository<User, ObjectId> {
   User findByUsername(String username);
   boolean existsByEmail(String email);
}
