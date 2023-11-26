package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.model.JournalEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JournalEntryRepo extends CrudRepository<JournalEntry, Long> {


    Set<JournalEntry> findJournalEntriesByUserId(Long userId);


}
