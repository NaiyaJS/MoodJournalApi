package com.passionProject.Mood.Journal.model;

import com.passionProject.Mood.Journal.enums.GeneralFeeling;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_entry_id")
    private long journalEntryId;

    @Enumerated(EnumType.STRING)
    private GeneralFeeling generalFeeling;
   @Column(name = "entry_date")
    private LocalDate entryDate;
   @Column(name = "notes")
   private String notes; //Additional notes about the day
   @Column(name = "weather")
   private String weather;
    @OneToMany(mappedBy = "journalEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MoodDetail> moodDetails;//change to collection if changing field type later
    @ManyToOne
    @JoinColumn(name = "user_Fkey_id")//foriegn key column in the JournalEntry table
    private User user;

    public JournalEntry() {
    }

    public JournalEntry(long journalEntryId, GeneralFeeling generalFeeling, LocalDate entryDate, String notes, String weather, Set<MoodDetail> moodDetails) {
        this.journalEntryId = journalEntryId;
        this.generalFeeling = generalFeeling;
        this.entryDate = entryDate;
        this.notes = notes;
        this.weather = weather;
        this.moodDetails = moodDetails;
    }

    public long getJournalEntryId() {
        return journalEntryId;
    }

    public void setJournalEntryId(long journalEntryId) {
        this.journalEntryId = journalEntryId;
    }

    public GeneralFeeling getGeneralFeeling() {
        return generalFeeling;
    }

    public void setGeneralFeeling(GeneralFeeling generalFeeling) {
        this.generalFeeling = generalFeeling;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Set<MoodDetail> getMoodDetails() {
        return moodDetails;
    }

    public void setMoodDetails(Set<MoodDetail> moodDetails) {
        this.moodDetails = moodDetails;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "journalEntryId=" + journalEntryId +
                ", generalFeeling=" + generalFeeling +
                ", entryDate=" + entryDate +
                ", notes='" + notes + '\'' +
                ", weather='" + weather + '\'' +
                ", moodDetails=" + moodDetails +
                '}';
    }
}
