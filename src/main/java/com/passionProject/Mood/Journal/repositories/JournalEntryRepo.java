package com.passionProject.Mood.Journal.repositories;

import com.passionProject.Mood.Journal.enums.GeneralFeeling;
import com.passionProject.Mood.Journal.model.JournalEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface JournalEntryRepo extends CrudRepository<JournalEntry, Long> {

    @Query(value = "SELECT * FROM journal_entry je WHERE je.user_id =:userId", nativeQuery = true)
    Set<JournalEntry> findJournalEntriesByUserId(@Param("userId") Long userId);

   //custom query to search forentries between different time frames
    @Query(value = "SELECT * FROM journal_entry je" + "WHERE je.user_id =:userId AND je.entry_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Set<JournalEntry> findJournalEntriesByUserIdAndDateRange(
            @Param("userId")Long userId, @Param("startDate")LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    //query to search entries by feeling
   @Query(value = "SELECT * FROM journal_entry je" + "WHERE je.user_id = :userId AND je.general_feeling = :feeling", nativeQuery = true)
    Set<JournalEntry>findJournalEntriesByUserIdAndFeeling(@Param("userId")Long userId, @Param("feeling") GeneralFeeling feeling);

    //searching entries by weather
    @Query(value = "SELECT * FROM journal_entry je " + "WHERE je.user_id = :userId AND LOWER(je.weather) LIKE LOWER(CONCAT('%', :weather, '%')) ", nativeQuery = true)
    Set<JournalEntry> findJournalEntriesByUserIdAndWeather (@Param("userId") Long userId, @Param("weather")String weather);

    //search by a combination of criteria
    @Query(value = "SELECT * FROM journal_entry je " +
            "WHERE je.user_id = :userId AND " +
            "(je.entry_date BETWEEN :startDate AND :endDate) AND " +
            "(je.general_feeling = :feeling) AND " +
            "LOWER(je.weather) LIKE LOWER(CONCAT('%', :weather, '%'))",
            nativeQuery = true)
    Set<JournalEntry> serachJournalEntries(
            @Param("userId") Long userId, @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate, @Param("feeling") GeneralFeeling feeling,
            @Param("weather") String weather
    );

    
}
