package com.example.diaryapp.data.dao

import androidx.room.*
import com.example.diaryapp.data.entity.DiaryEntry
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface DiaryDao {

    /**
     * すべての日記を取得（日付の新しい順）
     */
    @Query("SELECT * FROM diary_entries ORDER BY date DESC, createdAt DESC")
    fun getAllDiaries(): Flow<List<DiaryEntry>>

    /**
     * IDで日記を取得
     */
    @Query("SELECT * FROM diary_entries WHERE id = :id")
    suspend fun getDiaryById(id: Long): DiaryEntry?

    /**
     * 特定の日付の日記を取得
     */
    @Query("SELECT * FROM diary_entries WHERE date >= :startDate AND date < :endDate ORDER BY createdAt DESC")
    fun getDiariesByDate(startDate: Date, endDate: Date): Flow<List<DiaryEntry>>

    /**
     * タイトルまたは内容で検索
     */
    @Query("SELECT * FROM diary_entries WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY date DESC")
    fun searchDiaries(query: String): Flow<List<DiaryEntry>>

    /**
     * 日記を挿入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: DiaryEntry): Long

    /**
     * 日記を更新
     */
    @Update
    suspend fun updateDiary(diary: DiaryEntry)

    /**
     * 日記を削除
     */
    @Delete
    suspend fun deleteDiary(diary: DiaryEntry)

    /**
     * すべての日記を削除
     */
    @Query("DELETE FROM diary_entries")
    suspend fun deleteAllDiaries()

    /**
     * 日記の総数を取得
     */
    @Query("SELECT COUNT(*) FROM diary_entries")
    fun getDiaryCount(): Flow<Int>
}