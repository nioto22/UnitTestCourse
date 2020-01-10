package com.aprouxdev.unittestcourse.di;

import com.aprouxdev.unittestcourse.ui.notesList.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();
}
