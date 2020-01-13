package com.aprouxdev.unittestcourse.repository;

import com.aprouxdev.unittestcourse.models.Note;
import com.aprouxdev.unittestcourse.persistence.NoteDao;
import com.aprouxdev.unittestcourse.ui.Resource;
import com.aprouxdev.unittestcourse.util.TestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import io.reactivex.Single;

import static com.aprouxdev.unittestcourse.repository.NoteRepository.INSERT_FAILURE;
import static com.aprouxdev.unittestcourse.repository.NoteRepository.INSERT_SUCCESS;
import static com.aprouxdev.unittestcourse.repository.NoteRepository.UPDATE_FAILURE;
import static com.aprouxdev.unittestcourse.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NoteRepositoryTest {

    private static final Note NOTE1 = new Note(TestUtil.TEST_NOTE_1);

    // system under test
    private NoteRepository noteRepository;

    private NoteDao noteDao;

    @BeforeEach
    public void initEach(){

        //MockitoAnnotations.initMocks(this);  // The way to initiate a mock
        noteDao = mock(NoteDao.class);
        noteRepository = new NoteRepository(noteDao);
    }

    /*
        insert note
        verify the correct method is called
        confirm observer is triggered
        confirm new rows inserted
     */
    @Test
    void insertNote_returnRow() throws Exception {

        // Arrange
            //Define what Mockito has to do when we insert Note object
            final Long insertedRow = 1L;    // define the result we expect
            final Single<Long> returnedData = Single.just(insertedRow);  // define what it will be returned insertMethod return a Single<Long> object
            when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData); // define what mockito has to do

        // Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst();

        // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value : " + returnedValue.data);
        assertEquals(Resource.success(1, INSERT_SUCCESS), returnedValue);

    }

    // Same test using RxJava test : shorter but less explicit
    @Test
    void insertNote_returnRow_RxJavaTestWay() throws Exception {
        // Arrange
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        // Act and Assert
        noteRepository.insertNote(NOTE1)
                .test()
                .await()
                .assertValue(Resource.success(1, INSERT_SUCCESS));
    }

    /*
        Insert note
        Failure return -1
     */
    @Test
    void insertNote_returnFailure() throws Exception {
        // Arrange
            //Define what Mockito has to do when we insert Note object
            final Long failedInsert = -1L;    // define the result we expect
            final Single<Long> returnedData = Single.just(failedInsert);  // define what it will be returned insertMethod return a Sigle<Long> object
            when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData); // define what mockito has to do

        // Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst();

        // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, INSERT_FAILURE), returnedValue);
    }
    /*
        Insert note
        null title
        confirm throw exception
     */

    @Test
    void insertNote_nullTitle_throwException() throws Exception {

        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.insertNote(note);

            }
        });
    }

    /*
        update note
        verify correct method is call
        confirm the observer is trigger
        number of rows update
     */

    @Test
    void updateNote_returnNumRowsUpdated() throws Exception {
        // Arrange
        final int updatedRow = 1; // What we expect
        when(noteDao.updateNote(any(Note.class))).thenReturn(Single.just(updatedRow));  // Mockito work

        // Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(NOTE1).blockingFirst();

        // Assert
        verify(noteDao).updateNote(any(Note.class));  //Verify correct method call
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.success(updatedRow, UPDATE_SUCCESS), returnedValue);
    }


      /*
        update note
        Failure -1
     */

    @Test
    void updateNote_returnFailure() throws Exception {
        // Arrange
        final int failedInsert = -1;
        final Single<Integer> returnedData = Single.just(failedInsert);
        when(noteDao.updateNote(any(Note.class))).thenReturn(returnedData);

        // Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(NOTE1).blockingFirst();

        // Assert
        verify(noteDao).updateNote(any(Note.class));  //Verify correct method call
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, UPDATE_FAILURE), returnedValue);
    }
    /*
        update note
        null title
        throw exception
     */
    @Test
    void updateNote_nullTitle_throwException() throws Exception {

        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.updateNote(note);

            }
        });
    }


}
