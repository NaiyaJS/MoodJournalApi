package com.passionProject.Mood.Journal.controllers;

import com.passionProject.Mood.Journal.dto.ApiResponseBody;
import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.exceptions.UserNotFoundException;
import com.passionProject.Mood.Journal.model.JournalEntry;

import com.passionProject.Mood.Journal.service.JournalEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
//    @Autowired
//    MoodDetailService moodDetailService;


    //create entry
//    @PostMapping("/users/{userId}/journalEntry")
//    public ResponseEntity<JournalEntry> createJournalEntry(@PathVariable Long userId, @RequestBody JournalEntry journalEntry){
//
//        JournalEntry createdJournalEntry = journalEntryService.createJournalEntry(userId, journalEntry);
//
//        return new ResponseEntity<>(createdJournalEntry, HttpStatus.CREATED);
//
//    }
    @PostMapping("/users/{userId}/journalEntry")
    public ResponseEntity<ApiResponseBody> createJournalEntry(@PathVariable Long userId, @RequestBody JournalEntry journalEntry) {
        try {
            JournalEntry createdJournalEntry = journalEntryService.createJournalEntry(userId, journalEntry);

            ApiResponseBody responseBody = buildSuccessResponse("Journal Entry created successfully", createdJournalEntry);

            return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        } catch (JournalEntryNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("Journal Entry not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("User not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get entry by id
    @GetMapping("/journalEntry/{journalEntryId}")
    public ResponseEntity<ApiResponseBody> getJournalEntryById(@PathVariable Long journalEntryId) {
        try {
            JournalEntry journalEntry = journalEntryService.getJournalEntryById(journalEntryId);
            ApiResponseBody responseBody = buildSuccessResponse("Journal Entry retrieved successfully", journalEntry);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (JournalEntryNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("Journal Entry not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all journal entries
    @GetMapping("/journalEntries")
    public ResponseEntity<ApiResponseBody> getAllJournalEntries() {
        try {
            Iterable<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries();
            ApiResponseBody responseBody = buildSuccessResponse("Journal Entries retrieved successfully", journalEntries);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all journal entries for a user
    @GetMapping("/users/{userId}/journalEntries")
    public ResponseEntity<ApiResponseBody> getAllJournalEntriesForUser(@PathVariable Long userId){
        try {
            Set<JournalEntry> journalEntries = journalEntryService.getAllJournalEntriesForUser(userId);
            ApiResponseBody responseBody = buildSuccessResponse("Journal Entries for User retrieved successfully", journalEntries);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("User not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //update journal entry
    @PutMapping("/journalEntry/{journalEntryId}")
    public ResponseEntity<ApiResponseBody> updateJournalEntry(@PathVariable Long journalEntryId, @RequestBody JournalEntry journalEntry) {
        try {
            JournalEntry updatedEntry = journalEntryService.updateJournalEntry(journalEntryId, journalEntry);
            ApiResponseBody responseBody = buildSuccessResponse("Journal Entry updated successfully", updatedEntry);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (JournalEntryNotFoundException e) {
            ApiResponseBody errorBody = buildErrorResponse("Journal Entry not found", null);

            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseBody errorBody = buildErrorResponse("Internal Server Error", null);

            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //delete journal entry
    @DeleteMapping("/journalEntry/{journalEntryId}")
    public ResponseEntity<ApiResponseBody> deleteJournalEntry(@PathVariable Long journalEntryId) {
        try {
            journalEntryService.deleteJournalEntry(journalEntryId);
            ApiResponseBody responseBody = buildSuccessResponse("Journal Entry deleted successfully", null);

            return new ResponseEntity<>(responseBody, HttpStatus.NO_CONTENT);
        } catch (JournalEntryNotFoundException e) {

            ApiResponseBody errorBody = buildErrorResponse("Journal Entry not found", null);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    private ApiResponseBody buildSuccessResponse(String message, Object data) {
        return new ApiResponseBody(HttpStatus.OK.value(), message, data);
    }
    private ApiResponseBody buildErrorResponse(String message, Object data) {
        return new ApiResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }
}
