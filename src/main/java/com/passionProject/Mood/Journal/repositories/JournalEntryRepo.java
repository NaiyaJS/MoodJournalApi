package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.model.JournalEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JournalEntryRepo extends CrudRepository<JournalEntry, Long> {

    @Query(value = "SELECT * FROM journal_entry je WHERE je.user_id =:userId", nativeQuery = true)
    Set<JournalEntry> findJournalEntriesByUserId(@Param("userId") Long userId);


}
