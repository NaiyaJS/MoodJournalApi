package com.passionProject.Mood.Journal.service;

import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.model.JournalEntry;
import com.passionProject.Mood.Journal.repositories.JournalEntryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//get all moodDetails by journal entry id will be in moodDetail class
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    private final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    //verify
    public JournalEntry verifyJournalEntry(Long journalEntryId) throws JournalEntryNotFoundException {
        logger.info("Verifying journal entry with id: {}", journalEntryId);

        JournalEntry journalEntry = journalEntryRepo.findById(journalEntryId)
                .orElseThrow(() -> {
                    String errorMessage = "Journal Entry with id '" + journalEntryId + "' not found";
                    logger.error(errorMessage);
                    return new JournalEntryNotFoundException(errorMessage);
                });
        logger.info("Journal Entry '" + journalEntryId + "'verified successfully");
        return journalEntry;
    }

    //create entry
    public JournalEntry createJournalEntry(JournalEntry journalEntry){
        return journalEntryRepo.save(journalEntry);
    }
    //get entry by id
    public JournalEntry getJournalEntryById(Long journalEntryId) throws JournalEntryNotFoundException{
        Optional<JournalEntry> journalEntry = journalEntryRepo.findById(journalEntryId);
        return journalEntry.orElseThrow(() ->
                new JournalEntryNotFoundException("Journal Entry ith id '" + journalEntryId + "' was not found."));

    }
   //get all journal entries
   public Iterable<JournalEntry> getAllJournalEntries(){
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
                editJournalEntry.setWeather(journalEntry.getWeather());
                editJournalEntry.setMoodDetails(journalEntry.getMoodDetails());

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
