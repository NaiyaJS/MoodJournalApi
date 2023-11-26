package com.passionProject.Mood.Journal.enums;


public enum GeneralFeeling {
    //using unicode escape sequence for specific emoji's
    //when you want to ensure proper encoding in your source code
    HAPPY("\uD83D\uDE0A"),
    SAD("\uD83D\uDE22"),
    ANGRY("\uD83D\uDE21"),
    EXCITED("\uD83D\uDE04");

    private final String emoji;

    GeneralFeeling(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
