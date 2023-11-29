package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    //SELECT * FROM user WHERE user_id = :userId
     Optional<User> findbyId(Long userId);


}
