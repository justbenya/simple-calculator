package com.simplecalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EffectiveHandCollectionTest {
    @Test
    public void getDiscardTiles() throws Exception {
    }

    @Test
    public void calculatedUkeIre() throws Exception {
        EffectiveHandCollection effectiveHandCollection =
                new EffectiveHandCollection(new Hand("11268m446p12334s7z"));
        assertEquals(2, effectiveHandCollection.getDiscardTiles().get(1).getDiscardTile());

    }

}