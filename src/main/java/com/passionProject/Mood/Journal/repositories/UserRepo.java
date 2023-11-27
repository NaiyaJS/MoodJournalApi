package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
