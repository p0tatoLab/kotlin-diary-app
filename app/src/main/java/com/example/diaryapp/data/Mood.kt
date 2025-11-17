package com.example.diaryapp.data

enum class Mood(val emoji: String, val label: String) {
    GREAT("😊", "最高"),
    GOOD("🙂", "良い"),
    NEUTRAL("😐", "普通"),
    BAD("😞", "悪い"),
    TERRIBLE("😢", "最悪");

    companion object {
        fun fromOrdinal(ordinal: Int): Mood {
            return entries.getOrNull(ordinal) ?: NEUTRAL
        }
    }
}