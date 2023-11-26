package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.model.JournalEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepo extends CrudRepository<JournalEntry, Long> {

}
