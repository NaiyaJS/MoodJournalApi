package com.passionProject.Mood.Journal.controllers;

import com.passionProject.Mood.Journal.dto.ApiResponseBody;
import com.passionProject.Mood.Journal.exceptions.UserNotFoundException;
import com.passionProject.Mood.Journal.model.User;
import com.passionProject.Mood.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //create a new user
//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
//
//    //get all users
//    @GetMapping("/users")
//    public ResponseEntity<Iterable<User>> getAllUsers() {
//     Iterable<User>users = userService.getAllUsers();
//     return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    //get a user by id
//    @GetMapping("/users/{userId}")
//    public ResponseEntity<User> getUserById(@PathVariable Long userId){
//        try{
//            User user = userService.getUserById(userId);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }catch(UserNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    //update user
//    @PutMapping("/users/{userId}")
//    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
//        try {
//            User updatedUserResult = userService.updateUser(userId, updatedUser);
//            if (updatedUserResult != null) {
//                return new ResponseEntity<>(updatedUserResult, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (UserNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    //delete user
//    @DeleteMapping("/users/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
//        try {
//            userService.deleteUser(userId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (UserNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @PostMapping("/users")
    public ResponseEntity<ApiResponseBody> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);

            ApiResponseBody responseBody = buildSuccessResponse("User created successfully", createdUser);

            return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("User not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all users
    @GetMapping("/users")
    public ResponseEntity<ApiResponseBody> getAllUsers() {
        try {
            ApiResponseBody responseBody;
            Iterable<User> users = userService.getAllUsers();

            if (users.iterator().hasNext()) {
                responseBody = buildSuccessResponse("Users retrieved successfully", users);
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            } else {
                responseBody = buildSuccessResponse("No users found", users);
                return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get a user by id
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponseBody> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            ApiResponseBody responseBody = buildSuccessResponse("User retrieved successfully", user);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("User not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update user
    @PutMapping("/users/{userId}")
    public ResponseEntity<ApiResponseBody> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        try {
            User updatedUserResult = userService.updateUser(userId, updatedUser);
            ApiResponseBody responseBody = buildSuccessResponse("User updated successfully", updatedUserResult);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("User not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponseBody> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            ApiResponseBody responseBody = buildSuccessResponse("User deleted successfully", null);

            return new ResponseEntity<>(responseBody, HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("User not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ApiResponseBody buildSuccessResponse(String message, Object data) {
        return new ApiResponseBody(HttpStatus.OK.value(), message, data);
    }

    private ApiResponseBody buildErrorResponse(String message, Object data) {
        return new ApiResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }
}

