package com.passionProject.Mood.Journal.controllers;

import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.exceptions.MoodDetailNotFoundException;
import com.passionProject.Mood.Journal.model.JournalEntry;
import com.passionProject.Mood.Journal.model.MoodDetail;
import com.passionProject.Mood.Journal.service.JournalEntryService;
import com.passionProject.Mood.Journal.service.MoodDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoodDetailController {

    @Autowired
    private MoodDetailService moodDetailService;
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/journalEntry/{journalEntryId}/moodDetail")
    public ResponseEntity<MoodDetail> createMoodDetail(@RequestBody MoodDetail moodDetail, @PathVariable Long journalEntryId) {

        try {
            MoodDetail createdMoodDetail = moodDetailService.createMoodDetail(moodDetail, journalEntryId);
            return new ResponseEntity<>(createdMoodDetail, HttpStatus.CREATED);

        } catch (JournalEntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/moodDetails")
    public ResponseEntity<Iterable<MoodDetail>> getAllMoodDetails() {
        Iterable<MoodDetail> moodDetails = moodDetailService.getAllMoodDetails();
        return new ResponseEntity<>(moodDetails, HttpStatus.OK);
    }

    @GetMapping("/moodDetails/{moodDetailId}")
    public ResponseEntity<MoodDetail> getMoodDetailById(@PathVariable Long moodDetailId) {
        try {
            MoodDetail moodDetail = moodDetailService.getMoodDetailById(moodDetailId);
            return new ResponseEntity<>(moodDetail, HttpStatus.OK);
        } catch (MoodDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/moodDetails/{moodDetailId}")
    public ResponseEntity<MoodDetail> updateMoodDetail(@PathVariable Long moodDetailId, @RequestBody MoodDetail updatedMoodDetail) {
        try {
            MoodDetail updatedMoodDetailResult = moodDetailService.updateMoodDetail(moodDetailId, updatedMoodDetail);
            return new ResponseEntity<>(updatedMoodDetailResult, HttpStatus.OK);
        } catch (MoodDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/moodDetails/{moodDetailId}")
    public ResponseEntity<Void> deleteMoodDetail(@PathVariable Long moodDetailId) {
        try {
            moodDetailService.deleteMoodDetail(moodDetailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MoodDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}