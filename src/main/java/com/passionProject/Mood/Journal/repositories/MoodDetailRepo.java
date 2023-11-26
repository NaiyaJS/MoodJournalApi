package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.model.MoodDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodDetailRepo extends CrudRepository<MoodDetail, Long> {
}