package com.simplecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscardTileTest {
    @Test
    public void outs() throws Exception {
        int[] reduceTiles = new int[]{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        DiscardTile discardTile = new DiscardTile(1, reduceTiles, new Hand("11268m446p12334s7z"));
        assertEquals(15, discardTile.getOuts());
    }

}