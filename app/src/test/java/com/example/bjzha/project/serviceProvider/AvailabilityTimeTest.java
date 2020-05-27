package com.example.bjzha.project.serviceProvider;

import org.junit.Test;

import static org.junit.Assert.*;

public class AvailabilityTimeTest {

    @Test
    public void setDate() {
        AvailabilityTime time = new AvailabilityTime();
        String expected = "MONDAY";
        time.setDate("monday");
        String output = time.getDate();
        assertEquals(expected,output);
    }

    @Test
    public void setStartHour() {
        AvailabilityTime time = new AvailabilityTime();
        int expected = 10;
        time.setStartHour(10);
        int output = time.getStartHour();
        assertEquals(expected,output);
    }

    @Test
    public void setStartMin() {
        AvailabilityTime time = new AvailabilityTime();
        int expected = 10;
        time.setStartMin(10);
        int output = time.getStartMin();
        assertEquals(expected,output);
    }

    @Test
    public void setEndHour() {
        AvailabilityTime time = new AvailabilityTime();
        int expected = 10;
        time.setEndHour(10);
        int output = time.getEndHour();
        assertEquals(expected,output);
    }

    @Test
    public void setEndMin() {
        AvailabilityTime time = new AvailabilityTime();
        int expected = 10;
        time.setEndHour(10);
        int output = time.getEndHour();
        assertEquals(expected,output);
    }

}