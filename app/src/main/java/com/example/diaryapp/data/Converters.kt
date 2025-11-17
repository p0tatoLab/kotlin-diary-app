package com.example.diaryapp.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    /**
     * DateからLongへの変換
     */
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    /**
     * LongからDateへの変換
     */
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    /**
     * MoodからIntへの変換
     */
    @TypeConverter
    fun fromMood(mood: Mood): Int {
        return mood.ordinal
    }

    /**
     * IntからMoodへの変換
     */
    @TypeConverter
    fun toMood(ordinal: Int): Mood {
        return Mood.fromOrdinal(ordinal)
    }
}