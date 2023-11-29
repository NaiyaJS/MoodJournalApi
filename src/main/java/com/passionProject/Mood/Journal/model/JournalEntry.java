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

    private long journalEntryId;

    @Enumerated(EnumType.STRING)
    private GeneralFeeling generalFeeling;

    private LocalDate entryDate;

   private String notes; //Additional notes about the day

   private String weather;

    @ManyToOne
    @JoinColumn(name = "userId")//foriegn key column in the JournalEntry table
    private User user;
    @OneToMany(mappedBy = "journalEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MoodDetail> moodDetails;

    public JournalEntry() {
    }

    public Set<MoodDetail> getMoodDetails() {
        return moodDetails;
    }

    public void setMoodDetails(Set<MoodDetail> moodDetails) {
        this.moodDetails = moodDetails;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    @Override
    public String toString() {
        return "JournalEntry{" +
                "journalEntryId=" + journalEntryId +
                ", generalFeeling=" + generalFeeling +
                ", entryDate=" + entryDate +
                ", notes='" + notes + '\'' +
                ", weather='" + weather  +
                '}';
    }
}
