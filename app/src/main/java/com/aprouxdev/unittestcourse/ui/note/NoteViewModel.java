package com.aprouxdev.unittestcourse.ui.note;

import com.aprouxdev.unittestcourse.models.Note;
import com.aprouxdev.unittestcourse.repository.NoteRepository;
import com.aprouxdev.unittestcourse.ui.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static com.aprouxdev.unittestcourse.repository.NoteRepository.NOTE_TITLE_NULL;

public class NoteViewModel extends ViewModel {
    private static final String TAG = "NoteViewModel";

    // inject
    private final NoteRepository noteRepository;

    // vars
    private MutableLiveData<Note> note = new MutableLiveData<>();

    @Inject
    public NoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Conversion of RxJava Flowable from NoteRepository to LiveData when observe
    public LiveData<Resource<Integer>> insertNote() throws Exception{
       return LiveDataReactiveStreams.fromPublisher(
               noteRepository.insertNote(note.getValue())
       );
    }




    public LiveData<Note> observeNote(){
        return note;
    }

    public void setNote(Note note) throws Exception{
        if(note.getTitle() == null || note.getTitle().equals("")){
            throw new Exception(NOTE_TITLE_NULL);
        }
        this.note.setValue(note);
    }


}
