package com.simplecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShantenTest {
    @Test
    public void calculateShanten() throws Exception {
        Hand hand = new Hand("17m34p1224779s147z");
        assertEquals(4, hand.getShanten());

        Hand hand1 = new Hand("17m34p2224779s147z");
        assertEquals(4, hand1.getShanten());
    }

}