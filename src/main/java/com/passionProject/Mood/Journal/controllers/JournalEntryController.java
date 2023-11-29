package com.passionProject.Mood.Journal.controllers;

import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.model.JournalEntry;
import com.passionProject.Mood.Journal.model.MoodDetail;
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


    //create entry
    @PostMapping("/user/{userId}/journalEntry")
    public ResponseEntity<JournalEntry> createJournalEntry(@PathVariable Long userId, @RequestBody JournalEntry journalEntry){
        JournalEntry createdJournalEntry = journalEntryService.createJournalEntry(userId, journalEntry);
        return new ResponseEntity<>(createdJournalEntry, HttpStatus.CREATED);

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
    @GetMapping("/user/{userId}/journalEntries")
    public ResponseEntity<Set<JournalEntry>> getAllJournalEntriesForUser(@PathVariable Long userId){

        Set<JournalEntry> journalEntries = journalEntryService.getAllJournalEntriesForUser(userId);
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    //get all moodDetails by journal entry id
    @GetMapping("/journalEntry/{journalEntryId}/moodDetails")
    public ResponseEntity<Set<MoodDetail>> getAllMoodDetailsByJournalEntry(@PathVariable Long journalEntryId){
      try {
          Set<MoodDetail> moodDetails = journalEntryService.getAllMoodDetailsByJournalEntryId(journalEntryId);
          return new ResponseEntity<>(moodDetails, HttpStatus.OK);
      }catch(JournalEntryNotFoundException e){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    //update journal entry
    @PutMapping("journalEntry/{journalEntryId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable Long journalEntryId, @RequestBody JournalEntry journalEntry){
        try{
            JournalEntry updatedEntry = journalEntryService.updateJournalEntry(journalEntryId, journalEntry);
            return  new ResponseEntity<>(updatedEntry, HttpStatus.OK);
        }catch(JournalEntryNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //delete journal entry
    @DeleteMapping("journalEntry/{journalEntryId}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable Long journalEntryId){
        try{
            journalEntryService.deleteJournalEntry(journalEntryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(JournalEntryNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
