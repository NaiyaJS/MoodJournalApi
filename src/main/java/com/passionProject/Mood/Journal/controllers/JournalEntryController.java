package com.passionProject.Mood.Journal.controllers;

import com.passionProject.Mood.Journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/journalEntries")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    //create entry
    //get entry by id
    //get all journal entries
    //get all journal entries for a user
    //get all moodDetails by journal entry id
    //update journal entry
    //delete journal entry
}
