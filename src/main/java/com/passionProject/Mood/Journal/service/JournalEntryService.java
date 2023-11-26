package com.passionProject.Mood.Journal.service;

import com.passionProject.Mood.Journal.model.JournalEntry;
import com.passionProject.Mood.Journal.repositories.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public JournalEntry verifyJournalEntry(Long journalEntryId) throws JournalEntryNotFound{
        if(!JournalEntryRepo.existsById(journalEntryId)){
            throw new JournalEntryNotFoundException
        }
    }

}
