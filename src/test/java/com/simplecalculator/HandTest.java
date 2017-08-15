package com.simplecalculator;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HandTest {
    @Test
    public void hand() throws Exception {
        Hand hand = new Hand(new Reader("11268m446p12334s7z"));
        assertEquals(2, hand.getShanten());
        assertEquals(1, hand.getUkeIre().get(0).getDiscardTile());
    }
}