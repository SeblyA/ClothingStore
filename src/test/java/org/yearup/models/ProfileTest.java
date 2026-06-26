package org.yearup.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void getFirstName_shouldReturnFirstName() {
      //arrange
        Profile profile = new Profile();
        profile.setFirstName("sebly");
        //act
        String actual = profile.getFirstName();
        //assert
        assertEquals("sebly", actual);
    }
    @Test
    void getLastName_shouldReturnLastName() {
        Profile profile = new Profile();
        profile.setLastName("Assefa");
        String actual = profile.getLastName();
        assertEquals("Assefa", actual);
    }
}