package com.passionProject.Mood.Journal.response;

import com.passionProject.Mood.Journal.dto.ApiResponseBody;
import com.passionProject.Mood.Journal.model.JournalEntry;
import com.passionProject.Mood.Journal.model.User;
import com.passionProject.Mood.Journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryResponse {

    @Autowired
    private JournalEntryService journalEntryService;

    public ResponseEntity<?> createJournalEntry(Long userId, JournalEntry journalEntry ){

       // journalEntryService.verifyUser(userId);
        journalEntryService.createJournalEntry(userId, journalEntry);
            ApiResponseBody apiRB = new ApiResponseBody();
            apiRB.setData(journalEntry);
            apiRB.setStatusCode(HttpStatus.CREATED.value());
            apiRB.setMessage("Journal Entry created Successfully");

            return new ResponseEntity<>(apiRB, HttpStatus.CREATED);


    }

}
