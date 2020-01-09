package com.aprouxdev.unittestcourse.di;

import android.app.Application;

import com.aprouxdev.unittestcourse.persistence.NoteDao;
import com.aprouxdev.unittestcourse.persistence.NoteDatabase;
import com.aprouxdev.unittestcourse.repository.NoteRepository;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

import static com.aprouxdev.unittestcourse.persistence.NoteDatabase.DATABASE_NAME;

@Module
class AppModule {

    // Module for DAO and Repository
    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
        ).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase){
        return noteDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }
}
