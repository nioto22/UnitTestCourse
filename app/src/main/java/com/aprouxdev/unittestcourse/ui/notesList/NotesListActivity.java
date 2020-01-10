package com.aprouxdev.unittestcourse.ui.notesList;

import android.os.Bundle;
import android.util.Log;

import com.aprouxdev.unittestcourse.R;
import com.aprouxdev.unittestcourse.repository.NoteRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity {
    private static final String TAG = "NotesListActivity";
    @Inject
    NoteRepository noteRepository;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Log.d(TAG, "onCreate: " + noteRepository);
        
    }
}