package com.aprouxdev.unittestcourse.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.aprouxdev.unittestcourse.util.DateUtil.GET_MONTH_ERROR;
import static com.aprouxdev.unittestcourse.util.DateUtil.getMonthFromNumber;
import static com.aprouxdev.unittestcourse.util.DateUtil.monthNumbers;
import static com.aprouxdev.unittestcourse.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    private static final String today = "01-2020";

    @Test
    public void testGetCurrentTimestamp_returnedTimestamp(){
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
              assertEquals(today, DateUtil.getCurrentTimeStamp());
              System.out.println("Timestamp is generated correctly");
            }
        });
    }

    /*
        Parameterized Test
     */
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    public void getMonthFromNumber_returnSuccess(int monthNumber, TestInfo testInfo, TestReporter testReporter){
        assertEquals(months[monthNumber], DateUtil.getMonthFromNumber(monthNumbers[monthNumber]));
        System.out.println(monthNumbers[monthNumber] + " : " + months[monthNumber]);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11})
    public void testGetMonthFromNumber_returnError(int monthNumber, TestInfo testInfo, TestReporter testReporter){
        int randomInteger = new Random().nextInt(90) + 13;
        assertEquals(getMonthFromNumber(String.valueOf(monthNumber * randomInteger)), GET_MONTH_ERROR);
        System.out.println(monthNumbers[monthNumber] + " : " + GET_MONTH_ERROR);
    }
}
