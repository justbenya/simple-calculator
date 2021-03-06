package com.simplecalculator;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ReaderTest {

    @Test
    public void foundTilesInSuits() throws Exception {
        Reader hand = new Reader("255789m1346p2s127z");
        assertEquals("[255789, 1346, 2, 127]", Arrays.toString(hand.foundTilesInSuits()));

        Reader hand1 = new Reader("123456789p2s127z");
        assertEquals("[, 123456789, 2, 127]", Arrays.toString(hand1.foundTilesInSuits()));

        Reader hand2 = new Reader("25578913462s127z");
        assertEquals("[, , 25578913462, 127]", Arrays.toString(hand2.foundTilesInSuits()));

        Reader hand3 = new Reader("25578913462127z");
        assertEquals("[, , , 25578913462127]", Arrays.toString(hand3.foundTilesInSuits()));

        Reader hand4 = new Reader("111999m111999p11s");
        assertEquals("[111999, 111999, 11, ]", Arrays.toString(hand4.foundTilesInSuits()));

        Reader hand5 = new Reader("2557891346p2127z");
        assertEquals("[, 2557891346, , 2127]", Arrays.toString(hand5.foundTilesInSuits()));
    }

    @Test
    public void convertStringToSpecialArray() throws Exception {
        Reader hand = new Reader("255789m1346p2s127z");
        hand.convertStringToSpecialArray();
        assertEquals("[0, 0, 1, 0, 0, 2, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1]",
                Arrays.toString(hand.getHand()));

        Reader hand1 = new Reader("111999m111999p11s");
        hand1.convertStringToSpecialArray();
        assertEquals("[0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
                Arrays.toString(hand1.getHand()));
    }

    @Test
    public void createArray() throws Exception {
        Reader hand = new Reader("");
        int[] expected = new int[]{0, 4, 1, 0, 0, 2, 0, 1, 1, 4};
        assertEquals(Arrays.toString(expected),
                Arrays.toString(hand.createArray("1111111255789999")));
    }

}