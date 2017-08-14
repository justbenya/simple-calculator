package com.simplecalculator;

import java.util.Arrays;

/**
 * Тайл который уменьшает кол-во шантен
 */
public class DiscardTile {
    private int discardTile;
    private int[] tilesReduceShanten = new int[38];
    private int outs;

    public DiscardTile(int discardTile, int[] tilesReduceShanten, int outs) {
        this.discardTile = discardTile;
        this.tilesReduceShanten = tilesReduceShanten;
        this.outs = outs;
    }

    @Override
    public String toString() {
        return "\ndiscardTile = " + discardTile +
                ", tilesReduceShanten = " + Arrays.toString(tilesReduceShanten) +
                ", outs = " + outs;
    }
}
