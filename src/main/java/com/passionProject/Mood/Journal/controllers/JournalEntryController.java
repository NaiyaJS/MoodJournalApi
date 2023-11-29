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
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long journalEntryId){
        try{
            JournalEntry journalEntry = journalEntryService.getJournalEntryById(journalEntryId);
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }catch(JournalEntryNotFoundException e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get all journal entries
    @GetMapping("/journalEntries")
    public ResponseEntity<Iterable<JournalEntry>> getAllJournalEntries(){
        Iterable<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries();
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    //get all journal entries for a user
    @GetMapping("/users/{userId}/journalEntries")
    public ResponseEntity<Set<JournalEntry>> getAllJournalEntriesForUser(@PathVariable Long userId){

        Set<JournalEntry> journalEntries = journalEntryService.getAllJournalEntriesForUser(userId);
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }


    //update journal entry
    @PutMapping("/journalEntry/{journalEntryId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable Long journalEntryId, @RequestBody JournalEntry journalEntry){
        try{
            JournalEntry updatedEntry = journalEntryService.updateJournalEntry(journalEntryId, journalEntry);
            return  new ResponseEntity<>(updatedEntry, HttpStatus.OK);
        }catch(JournalEntryNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //delete journal entry
    @DeleteMapping("/journalEntry/{journalEntryId}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable Long journalEntryId){
        try{
            journalEntryService.deleteJournalEntry(journalEntryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(JournalEntryNotFoundException e){
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
