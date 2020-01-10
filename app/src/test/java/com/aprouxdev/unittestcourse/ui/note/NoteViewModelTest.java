package com.aprouxdev.unittestcourse.ui.note;

import com.aprouxdev.unittestcourse.models.Note;
import com.aprouxdev.unittestcourse.repository.NoteRepository;
import com.aprouxdev.unittestcourse.ui.Resource;
import com.aprouxdev.unittestcourse.util.InstantExecutorExtension;
import com.aprouxdev.unittestcourse.util.LiveDataTestUtil;
import com.aprouxdev.unittestcourse.util.TestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;

import static com.aprouxdev.unittestcourse.repository.NoteRepository.INSERT_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {

    // system under test
    private NoteViewModel noteViewModel;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        noteViewModel = new NoteViewModel(noteRepository);
    }


    /*
        Can't observe a note that hasn't been set == new note button was clicked
     */
    @Test
    void observeEmptyNote_whenNotSet() throws Exception {

        // Arrange
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();
        // Act
        Note note = liveDataTestUtil.getValue(noteViewModel.observeNote());
        // Assert
        assertNull(note);
    }

    /*
        Observe a note has been set and onChanged will trigger in activity
     */

    @Test
    void observeNote_whenSet() throws Exception {

        // Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();
        // Act
        noteViewModel.setNote(note);
        Note observedNote = liveDataTestUtil.getValue(noteViewModel.observeNote());
        // Assert
        assertEquals(note, observedNote);
    }


    /*
        Insert a new note and observe row returned
     */
    @Test
    void insertNote_returnRow() throws Exception {

        // Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        final int insertedRow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(insertedRow, INSERT_SUCCESS));
        when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);

        // Act
        noteViewModel.setNote(note);
        Resource<Integer> returnedValue = liveDataTestUtil.getValue(noteViewModel.insertNote());

        // Assert
        assertEquals(Resource.success(insertedRow, INSERT_SUCCESS), returnedValue);

    }

    /*
        insert :  don't return a new row without observer
     */
    @Test
    void dontReturnInsertRowWithoutObserver() throws Exception {
        // Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);

        // Act
        noteViewModel.setNote(note);

        // Assert
        verify(noteRepository, never()).insertNote(any(Note.class));
            // Mockito verify noteRepository was never used
    }
    
    
    /*
        set note, null title, throw exception
     */
    @Test
    void setNot_nullTitle_throwException() throws Exception {
        // Arrange
        final Note note = new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);

        // Assert
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {

                // Act
                noteViewModel.setNote(note);
            }
        });
    }
}
