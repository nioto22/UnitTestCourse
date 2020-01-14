package com.aprouxdev.unittestcourse.ui.notesList;

import android.content.Intent;
import android.os.Bundle;

import com.aprouxdev.unittestcourse.R;
import com.aprouxdev.unittestcourse.ui.note.NoteActivity;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity {
    private static final String TAG = "NotesListActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
        
    }
}
