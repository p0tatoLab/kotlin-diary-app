package com.example.diaryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.entity.DiaryEntry
import com.example.diaryapp.data.repository.DiaryRepository
import com.example.diaryapp.ui.state.DiaryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DiaryListState())
    val state: StateFlow<DiaryListState> = _state.asStateFlow()

    init {
        loadDiaries()
    }

    /**
     * 日記を読み込む
     */
    private fun loadDiaries() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            repository.getAllDiaries()
                .catch { e ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                .collect { diaries ->
                    _state.value = _state.value.copy(
                        diaries = diaries,
                        isLoading = false,
                        error = null
                    )
                }
        }
    }

    /**
     * 日記を削除
     */
    fun deleteDiary(diary: DiaryEntry) {
        viewModelScope.launch {
            try {
                repository.deleteDiary(diary)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    /**
     * 検索
     */
    fun searchDiaries(query: String) {
        if (query.isBlank()) {
            loadDiaries()
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            repository.searchDiaries(query)
                .catch { e ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                .collect { diaries ->
                    _state.value = _state.value.copy(
                        diaries = diaries,
                        isLoading = false,
                        error = null
                    )
                }
        }
    }
}