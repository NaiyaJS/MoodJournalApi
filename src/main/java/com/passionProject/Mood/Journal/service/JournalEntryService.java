package com.passionProject.Mood.Journal.service;

import com.passionProject.Mood.Journal.dto.ErrorDetails;
import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.model.JournalEntry;

import com.passionProject.Mood.Journal.model.User;
import com.passionProject.Mood.Journal.repositories.JournalEntryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//get all moodDetails by journal entry id will be in moodDetail class
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    //verify
    public JournalEntry verifyJournalEntry(Long journalEntryId){

        logger.info("Verifying journal entry with id: {}", journalEntryId);

        return journalEntryRepo.findById(journalEntryId)
                    .orElseThrow(() -> new JournalEntryNotFoundException("Journal Entry ith id '" + journalEntryId + "' not found"));

    }

    //create entry

    public JournalEntry createJournalEntry(Long userId , JournalEntry journalEntry){
        logger.info("Creating Journal entry for user with id {}", userId);
        try {
            //verifyUser(userId);
            User user = userService.verifyUser(userId);

            //Additional validations or modifications before saving entry
            journalEntry.setUser(user);

            //save entry
            JournalEntry saveEntry = journalEntryRepo.save(journalEntry);

            logger.info("Successfully created Journal Entry");
            return saveEntry;
        }catch (Exception e) {
            logger.error("Error creating Journal Entry", e);
            throw e;
        }

    }
    //get entry by id
    public JournalEntry getJournalEntryById(Long journalEntryId) throws JournalEntryNotFoundException{
        Optional<JournalEntry> journalEntry = journalEntryRepo.findById(journalEntryId);
        return journalEntry.orElseThrow(() ->
                new JournalEntryNotFoundException("Journal Entry ith id '" + journalEntryId + "' was not found."));

    }
   //get all journal entries
   public Iterable<JournalEntry> getAllJournalEntries(){
        logger.info("Getting all Journal Entries");
        return journalEntryRepo.findAll();
   }

    //get all journal entries for a user
    public Set<JournalEntry> getAllJournalEntriesForUser(Long userId){
        return journalEntryRepo.findJournalEntriesByUserId(userId);
    }


    //update journal entry
    public JournalEntry updateJournalEntry(Long journalEntryId, JournalEntry journalEntry) throws JournalEntryNotFoundException {
            Optional<JournalEntry> findJournalEntryById = journalEntryRepo.findById(journalEntryId);

            if(findJournalEntryById.isPresent()){

                JournalEntry editJournalEntry = findJournalEntryById.get();
                editJournalEntry.setGeneralFeeling(journalEntry.getGeneralFeeling());
                editJournalEntry.setEntryDate(journalEntry.getEntryDate());
                editJournalEntry.setNotes(journalEntry.getNotes());
                editJournalEntry.setDetailedEntry(journalEntry.getDetailedEntry());
                editJournalEntry.setWeather(journalEntry.getWeather());


                journalEntryRepo.save(editJournalEntry);

                logger.info("Journal was successfully Updated");
                return editJournalEntry;
            }else{
                logger.error("Unsuccessful attempt to edite. Journal Entry not found.");
                throw new JournalEntryNotFoundException("Error updating Journal entry with Id" + journalEntryId);
            }
    }
    //delete journal entry with id
    public void deleteJournalEntry(Long journalEntryId) throws JournalEntryNotFoundException{
        verifyJournalEntry(journalEntryId);
        logger.info("Journal Entry has Successfully been deleted");
        journalEntryRepo.deleteById(journalEntryId);

    }


}
