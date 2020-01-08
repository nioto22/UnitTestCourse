package com.aprouxdev.unittestcourse;

import com.aprouxdev.unittestcourse.persistence.NoteDao;
import com.aprouxdev.unittestcourse.persistence.NoteDatabase;

import org.junit.After;
import org.junit.Before;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

public abstract class NoteDatabaseTest {
    // Abstract test method called by instrumentation test to test Dao
    // That's means Create a database and get dao
    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init(){
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish(){
        noteDatabase.close();
    }

}
