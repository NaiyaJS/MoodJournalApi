package com.passionProject.Mood.Journal.service;

import com.passionProject.Mood.Journal.exceptions.MoodDetailNotFoundException;
import com.passionProject.Mood.Journal.model.MoodDetail;
import com.passionProject.Mood.Journal.repositories.MoodDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoodDetailService {

    @Autowired
    private MoodDetailRepo moodDetailRepo;

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
    public MoodDetail createMoodDetail(MoodDetail moodDetail){

        MoodDetail savedMoodDetail = moodDetailRepo.save(moodDetail);
        logger.info("Successfullly created MoodDetail");
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
