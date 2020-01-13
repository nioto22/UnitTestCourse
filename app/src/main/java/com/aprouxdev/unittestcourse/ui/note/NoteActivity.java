package com.aprouxdev.unittestcourse.ui.note;

import android.os.Bundle;

import com.aprouxdev.unittestcourse.R;

import dagger.android.support.DaggerAppCompatActivity;

public class NoteActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }
}
