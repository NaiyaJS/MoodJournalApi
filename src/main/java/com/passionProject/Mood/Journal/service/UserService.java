package com.passionProject.Mood.Journal.service;

import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.exceptions.UserNotFoundException;
import com.passionProject.Mood.Journal.model.User;
import com.passionProject.Mood.Journal.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    //verify user
    public User verifyUser(Long userId) throws UserNotFoundException {

        logger.info("Verifying user with id {} ....", userId);
        Optional<User> user = userRepo.findById(userId);

        return user.orElseThrow(() ->{
            String errorMessage = "User with id '" + userId + "' was not found";
            logger.error(errorMessage);
            return new UserNotFoundException(errorMessage);
        });
    }
    //create a User
    public User createUser(User user){
        User savedUser = userRepo.save(user);

        logger.info("Successfully created user");
        return savedUser;
    }




    //get user by id
    public User getUserById(Long userId) throws UserNotFoundException{
        logger.info("Getting user by id {} ...", userId);
        return userRepo.findById(userId)
            .orElseThrow(() -> {
                String errorMessage = "User with id ;" + userId + "' was not found";
                logger.error(errorMessage);
                return new UserNotFoundException(errorMessage);
        });
    }



    //get of all users use a list
    public Iterable<User> getAllUsers(){
       logger.info("Getting all users ...");
        return userRepo.findAll();
    }



    //update a User
    public User updateUser(Long userId, User updatedUser) throws UserNotFoundException{
        Optional<User> user = userRepo.findById(userId);

        if (user.isPresent()){
            User existingUser = user.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            userRepo.save(existingUser);

            logger.info("User with id {} was successfully updated", userId);
            return existingUser;
        }
        return null;
    }


    //delete a user
    public void deleteUser(Long userId) throws UserNotFoundException{
        verifyUser(userId);
        logger.info("User with id {} has successfully been deleted", userId);
        userRepo.deleteById(userId);
    }


}
