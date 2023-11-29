//package com.passionProject.Mood.Journal.model;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class MoodDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    private long moodDetailId;
//
//    @ManyToOne
//    @JoinColumn(name = "journalEntryId")//foreign key column
//    private JournalEntry journalEntry;
//
//    private LocalDateTime entryDateTime;//time of day when the detailed entry was written
//    @Lob //database should treat this field as a large text object. This is useful when you expect the text data
//         // to be larger than what a typical VARCHAR column can accommodate
//    private String detailedEntry;
//
//    public MoodDetail() {
//    }
//
//
//
//    public long getMoodDetailId() {
//        return moodDetailId;
//    }
//
//    public void setMoodDetailId(long moodDetailId) {
//        this.moodDetailId = moodDetailId;
//    }
//
//    public JournalEntry getJournalEntry() {
//        return journalEntry;
//    }
//
//    public void setJournalEntry(JournalEntry journalEntry) {
//        this.journalEntry = journalEntry;
//    }
//
//    public LocalDateTime getEntryDateTime() {
//        return entryDateTime;
//    }
//
//    public void setEntryDateTime(LocalDateTime entryDateTime) {
//        this.entryDateTime = entryDateTime;
//    }
//
//    public String getDetailedEntry() {
//        return detailedEntry;
//    }
//
//    public void setDetailedEntry(String detailedEntry) {
//        this.detailedEntry = detailedEntry;
//    }
//
//    @Override
//    public String toString() {
//        return "MoodDetail{" +
//                "moodDetailId=" + moodDetailId +
//                ", journalEntry=" + journalEntry +
//                ", entryDateTime=" + entryDateTime +
//                ", detailedEntry='" + detailedEntry + '\'' +
//                '}';
//    }
//}
