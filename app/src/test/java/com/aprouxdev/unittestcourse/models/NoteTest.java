package com.aprouxdev.unittestcourse.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NoteTest {

    public static final String TIMESTAMP_1 = "04-05-2019";
    public static final String TIMESTAMP_2 = "06-06-2019";

    /*
    Compare tow equal notes
     */

    @Test
    void isNotesEqual_identicalProperties_returnTrue() throws Exception {
        //Name : test_param_result
        // Arrange
        Note note1 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note2.setId(1);
        // Act

        // Assert
        assertEquals(note1, note2);
        System.out.println("Test pass : notes are equals with identical properties");
    }
    /*
        Compare notes with different id
     */

    @Test
    void isNotesEqual_differentId_returnFalse() throws Exception {
        //Name : test_param_result
        // Arrange
        Note note1 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note2.setId(2);
        // Act

        // Assert
        assertNotEquals(note1, note2);
        System.out.println("Test pass : notes different with different Id");

    }
    /*
        Compare two notes with different timestamps
     */
    @Test
    void isNotesEqual_differentTimestamp_returnTrue() throws Exception {
        //Name : test_param_result
        // Arrange
        Note note1 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Title#1", "Content#1", TIMESTAMP_2);
        note2.setId(1);
        // Act

        // Assert
        assertEquals(note1, note2);
        System.out.println("Test pass : notes are equal with different Timestamp");

    }

    /*
        Compare two notes with differenty titles
     */
    @Test
    void isNotesEqual_differentTitle_returnFalse() throws Exception {
        //Name : test_param_result
        // Arrange
        Note note1 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Title#2", "Content#1", TIMESTAMP_1);
        note2.setId(1);
        // Act

        // Assert
        assertNotEquals(note1, note2);
        System.out.println("Test pass : notes are different with different Title");

    }

    /*
        Compare two notes with different content
     */
    @Test
    void isNotesEqual_differentContent_returnFalse() throws Exception {
        //Name : test_param_result
        // Arrange
        Note note1 = new Note("Title#1", "Content#1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Title#1", "Content#2", TIMESTAMP_1);
        note2.setId(1);
        // Act

        // Assert
        assertNotEquals(note1, note2);
        System.out.println("Test pass : notes are different with different Title");

    }


}
