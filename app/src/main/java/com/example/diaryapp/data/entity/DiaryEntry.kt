package com.example.diaryapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diaryapp.data.Mood
import java.util.Date

@Entity(tableName = "diary_entries")
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,

    val content: String,

    val mood: Mood = Mood.NEUTRAL,

    val date: Date = Date(),

    val createdAt: Date = Date(),

    val updatedAt: Date = Date()
) {
    /**
     * プレビュー用のコンテンツ（最初の100文字）
     */
    fun getPreview(): String {
        return if (content.length > 100) {
            content.take(100) + "..."
        } else {
            content
        }
    }

    /**
     * 空の日記かどうか
     */
    fun isEmpty(): Boolean {
        return title.isBlank() && content.isBlank()
    }
}