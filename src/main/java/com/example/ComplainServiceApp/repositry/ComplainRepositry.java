package com.example.ComplainServiceApp.repositry;

import com.example.ComplainServiceApp.entity.Complain;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainRepositry extends MongoRepository<Complain, String> {

    List<Complain> findByTypeAndSolvedAndLevel(
            String type,
            Boolean solved,
            String level
    );
}
