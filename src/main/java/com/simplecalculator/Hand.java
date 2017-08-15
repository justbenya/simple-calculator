package com.simplecalculator;

import java.util.ArrayList;

public class Hand {

    private final Shanten shanten;
    private final EffectiveHandCollection ukeIre;
    private int[] hand;

    public Hand(String string) {
        this.hand = new Reader(string).getHand();
        this.shanten = new Shanten(this);
        this.ukeIre = new EffectiveHandCollection(this);
    }

    public Hand(Reader reader) {
        this.hand = reader.getHand();
        this.shanten = new Shanten(this);
        this.ukeIre = new EffectiveHandCollection(this);
    }

    public int[] getHand() {
        return this.hand;
    }

    public int getShanten() {
        return this.shanten.calculate();
    }

    public ArrayList<DiscardTile> getUkeIre() {
        return this.ukeIre.getDiscardTiles();
    }

}
