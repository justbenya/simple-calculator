package com.simplecalculator;

import java.util.Arrays;

/**
 * Тайл который уменьшает кол-во шантен
 */
public class DiscardTile {

    /**
     * Тайл который нужно сбросить
     */
    private int discardTile;

    /**
     * Тайлы которые нужны для уменьшения шантен
     */
    private int[] tilesReduceShanten = new int[38];

    /**
     * Total number(Uke-Ire) of tiles currently available that would reduce Shanten.
     */
    private int outs;

    /**
     * Наша рука
     */
    private Hand hand;

    public DiscardTile(int discardTile, int[] tilesReduceShanten, Hand hand) {
        this.discardTile = discardTile;
        this.tilesReduceShanten = tilesReduceShanten;
        this.hand = hand;
        this.outs = calculateOuts();
    }

    public int getDiscardTile() {
        return discardTile;
    }

    public int[] getTilesReduceShanten() {
        return tilesReduceShanten;
    }

    public int getOuts() {
        return outs;
    }

    @Override
    public String toString() {
        return "\ndiscardTile = " + discardTile +
                ", tilesReduceShanten = " + Arrays.toString(tilesReduceShanten) +
                ", calculateOuts = " + outs;
    }

    /**
     * Считаем кол-во аутов
     * @return кол-во аутов
     */
    private int calculateOuts() {
        int[] hand = this.hand.getHand();
        int match = 0;
        int tilesCount = 0;

        for (int i = 0; i < hand.length; i++) {
            // Кол-во тайлов уменьшаюшие шантен
            if (this.tilesReduceShanten[i] > 0) {
                tilesCount++;
            }
            // Ищем совпадения с тайлами в руке
            if (this.tilesReduceShanten[i] > 0 && this.tilesReduceShanten[i] <= hand[i]) {
                match += hand[i];
            }
        }
        return 4 * tilesCount - match;
    }
}
