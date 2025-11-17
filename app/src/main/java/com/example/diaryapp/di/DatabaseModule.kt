package com.example.diaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.diaryapp.data.dao.DiaryDao
import com.example.diaryapp.data.database.DiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDiaryDatabase(
        @ApplicationContext context: Context
    ): DiaryDatabase {
        return Room.databaseBuilder(
            context,
            DiaryDatabase::class.java,
            DiaryDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDiaryDao(database: DiaryDatabase): DiaryDao {
        return database.diaryDao()
    }
}