package com.example.diaryapp.data.repository

import com.example.diaryapp.data.dao.DiaryDao
import com.example.diaryapp.data.entity.DiaryEntry
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao
) {
    /**
     * すべての日記を取得
     */
    fun getAllDiaries(): Flow<List<DiaryEntry>> {
        return diaryDao.getAllDiaries()
    }

    /**
     * IDで日記を取得
     */
    suspend fun getDiaryById(id: Long): DiaryEntry? {
        return diaryDao.getDiaryById(id)
    }

    /**
     * 特定の日付の日記を取得
     */
    fun getDiariesByDate(date: Date): Flow<List<DiaryEntry>> {
        val startOfDay = Date(date.time - date.time % (24 * 60 * 60 * 1000))
        val endOfDay = Date(startOfDay.time + 24 * 60 * 60 * 1000)
        return diaryDao.getDiariesByDate(startOfDay, endOfDay)
    }

    /**
     * 検索
     */
    fun searchDiaries(query: String): Flow<List<DiaryEntry>> {
        return diaryDao.searchDiaries(query)
    }

    /**
     * 日記を挿入
     */
    suspend fun insertDiary(diary: DiaryEntry): Long {
        return diaryDao.insertDiary(diary)
    }

    /**
     * 日記を更新
     */
    suspend fun updateDiary(diary: DiaryEntry) {
        diaryDao.updateDiary(diary)
    }

    /**
     * 日記を削除
     */
    suspend fun deleteDiary(diary: DiaryEntry) {
        diaryDao.deleteDiary(diary)
    }

    /**
     * すべての日記を削除
     */
    suspend fun deleteAllDiaries() {
        diaryDao.deleteAllDiaries()
    }

    /**
     * 日記の総数
     */
    fun getDiaryCount(): Flow<Int> {
        return diaryDao.getDiaryCount()
    }
}