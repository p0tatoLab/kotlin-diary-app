package com.example.diaryapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.Mood
import com.example.diaryapp.data.entity.DiaryEntry
import com.example.diaryapp.data.repository.DiaryRepository
import com.example.diaryapp.ui.state.DiaryDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DiaryDetailState())
    val state: StateFlow<DiaryDetailState> = _state.asStateFlow()

    private val diaryId: Long? = savedStateHandle.get<Long>("diaryId")

    init {
        diaryId?.let { loadDiary(it) }
    }

    /**
     * 日記を読み込む
     */
    private fun loadDiary(id: Long) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val diary = repository.getDiaryById(id)
                diary?.let {
                    _state.value = _state.value.copy(
                        diary = it,
                        title = it.title,
                        content = it.content,
                        mood = it.mood,
                        date = it.date,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    /**
     * タイトルを更新
     */
    fun updateTitle(title: String) {
        _state.value = _state.value.copy(title = title)
    }

    /**
     * 内容を更新
     */
    fun updateContent(content: String) {
        _state.value = _state.value.copy(content = content)
    }

    /**
     * 気分を更新
     */
    fun updateMood(mood: Mood) {
        _state.value = _state.value.copy(mood = mood)
    }

    /**
     * 日付を更新
     */
    fun updateDate(date: Date) {
        _state.value = _state.value.copy(date = date)
    }

    /**
     * 日記を保存
     */
    fun saveDiary() {
        viewModelScope.launch {
            try {
                val currentState = _state.value

                if (currentState.title.isBlank()) {
                    _state.value = _state.value.copy(error = "タイトルを入力してください")
                    return@launch
                }

                val diary = currentState.diary?.copy(
                    title = currentState.title,
                    content = currentState.content,
                    mood = currentState.mood,
                    date = currentState.date,
                    updatedAt = Date()
                ) ?: DiaryEntry(
                    title = currentState.title,
                    content = currentState.content,
                    mood = currentState.mood,
                    date = currentState.date
                )

                if (diary.id == 0L) {
                    repository.insertDiary(diary)
                } else {
                    repository.updateDiary(diary)
                }

                _state.value = _state.value.copy(isSaved = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    /**
     * エラーをクリア
     */
    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}