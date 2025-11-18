package com.example.diaryapp.ui.state

import com.example.diaryapp.data.entity.DiaryEntry

data class DiaryListState(
    val diaries: List<DiaryEntry> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)