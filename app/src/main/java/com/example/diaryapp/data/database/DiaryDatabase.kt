package com.example.diaryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diaryapp.data.Converters
import com.example.diaryapp.data.dao.DiaryDao
import com.example.diaryapp.data.entity.DiaryEntry

@Database(
    entities = [DiaryEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DiaryDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao

    companion object {
        const val DATABASE_NAME = "diary_database"
    }
}