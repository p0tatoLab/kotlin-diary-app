package com.example.diaryapp.ui.state

import com.example.diaryapp.data.Mood
import com.example.diaryapp.data.entity.DiaryEntry
import java.util.Date

data class DiaryDetailState(
    val diary: DiaryEntry? = null,
    val title: String = "",
    val content: String = "",
    val mood: Mood = Mood.NEUTRAL,
    val date: Date = Date(),
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)