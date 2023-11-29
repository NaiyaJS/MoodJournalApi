package com.passionProject.Mood.Journal.service;

import com.passionProject.Mood.Journal.exceptions.JournalEntryNotFoundException;
import com.passionProject.Mood.Journal.exceptions.MoodDetailNotFoundException;
import com.passionProject.Mood.Journal.model.JournalEntry;
import com.passionProject.Mood.Journal.model.MoodDetail;
import com.passionProject.Mood.Journal.repositories.MoodDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MoodDetailService {

    @Autowired
    private MoodDetailRepo moodDetailRepo;
    @Autowired
    private JournalEntryService journalEntryService;

    private Logger logger = LoggerFactory.getLogger(MoodDetailService.class);

    public void verifyMoodDetail(Long moodDetailId) throws MoodDetailNotFoundException{
        logger.info("Verifying MoodDetail with id {}", moodDetailId);

        MoodDetail moodDetail = moodDetailRepo.findById(moodDetailId)
              .orElseThrow(() -> {
                  String errorMessage = "MoodDetail with id '" + moodDetailId + "' not found";
                  logger.error(errorMessage);
                  return new MoodDetailNotFoundException(errorMessage);
              });

        logger.info("MoodDetail '" + moodDetailId + "' verified Successfully");
    }
    public MoodDetail createMoodDetail(MoodDetail moodDetail, Long journalEntryId) throws JournalEntryNotFoundException {
        JournalEntry journalEntry1 = journalEntryService.getJournalEntryById(journalEntryId);
        moodDetail.setJournalEntry(journalEntry1);

        MoodDetail savedMoodDetail = moodDetailRepo.save(moodDetail);
        logger.info("Successfully created MoodDetail");
        return savedMoodDetail;
    }

    public Iterable<MoodDetail> getAllMoodDetails(){
        return moodDetailRepo.findAllMoodDetails();
    }
    public MoodDetail getMoodDetailById(Long moodDetailId) throws MoodDetailNotFoundException {
        Optional<MoodDetail> moodDetail = moodDetailRepo.findById(moodDetailId);
        return moodDetail.orElseThrow(() ->
                new MoodDetailNotFoundException("MoodDetail with id '" + moodDetailId + "' was not found."));
    }
    //get all mood details by Journal entry Id
    public Set<MoodDetail> getAllMoodDetailsByJournalEntryId(Long journalEntryId) throws JournalEntryNotFoundException{
        JournalEntry journalEntry = journalEntryService.verifyJournalEntry(journalEntryId);
        return journalEntry.getMoodDetails();
    }

    public MoodDetail updateMoodDetail(Long moodDetailId, MoodDetail updatedMoodDetail) throws MoodDetailNotFoundException {
        Optional<MoodDetail> optionalMoodDetail = moodDetailRepo.findById(moodDetailId);

        if (optionalMoodDetail.isPresent()) {
            MoodDetail existingMoodDetail = optionalMoodDetail.get();
            existingMoodDetail.setEntryDateTime(updatedMoodDetail.getEntryDateTime());
            existingMoodDetail.setDetailedEntry(updatedMoodDetail.getDetailedEntry());

            moodDetailRepo.save(existingMoodDetail);

            logger.info("MoodDetail was successfully updated");
            return existingMoodDetail;
        } else {
            logger.error("Unsuccessful attempt to update. MoodDetail not found.");
            throw new MoodDetailNotFoundException("Error updating MoodDetail with Id" + moodDetailId);
        }
    }

    public void deleteMoodDetail(Long moodDetailId) throws MoodDetailNotFoundException {
        verifyMoodDetail(moodDetailId);
        logger.info("MoodDetail has successfully been deleted");
        moodDetailRepo.deleteById(moodDetailId);
    }


}
