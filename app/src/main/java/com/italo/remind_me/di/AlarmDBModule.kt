package com.italo.remind_me.di

import android.content.Context
import androidx.room.Room
import com.italo.remind_me.dao.AlarmDao
import com.italo.remind_me.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AlarmDBModule {

    @Provides
    fun provideServiceDao(appDatabase: AppDatabase): AlarmDao {
        return appDatabase.alarmDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME).build()
    }

    companion object {
        const val DB_NAME = "remindme_db"
    }
}
