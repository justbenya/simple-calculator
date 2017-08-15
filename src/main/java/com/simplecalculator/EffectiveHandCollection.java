
package com.simplecalculator;

import java.util.ArrayList;

public class EffectiveHandCollection {

    private Hand hand;
    private final ArrayList<DiscardTile> discardTiles;

    public EffectiveHandCollection(Hand hand) {
        this.hand = hand;
        this.discardTiles = new ArrayList<>();
        calculatedUkeIre();
        // Отсортируем руку по кол-ву аутов
//        Collections.sort(wdList, Collections.reverseOrder(WhatDiscard.COMPARE_BY_OUTS));
    }
    
    private void calculatedUkeIre() {
        int shanten = new Shanten(hand).calculate();
        int[] handSpecialArray = this.hand.getHand();

        for (int tileToDiscard = 0; tileToDiscard < 38; tileToDiscard++) {
             // Пропускаем все пустые(нулевые) ячейки
            if (handSpecialArray[tileToDiscard] == 0) {
                continue;
            }

            // Убираем 1 тайл 
            handSpecialArray[tileToDiscard]--;
            
            // Узнаем тайлы уменьшаюшие кол-во шантен
            int[] tilesReduceShanten = tilesReduceShanten(handSpecialArray, shanten);
            
            // Если массив не состоит из нулей, т.е. есть тайлы
            if (isNotNull(tilesReduceShanten)) {
                discardTiles.add(new DiscardTile(tileToDiscard, tilesReduceShanten, this.hand));
            }
            
            // Восстанавливаем удаленный тайл
            handSpecialArray[tileToDiscard]++;
        }
    }

    private int[] tilesReduceShanten(int[] handSpecialArray, int shanten) {
        int[] upgradeTiles = new int[38];

        // Теперь берем все 34 тайла
        for (int i = 0; i < 38; i++) {
            // Кроме этих разделителей
            if ((i == 0) || (i == 10) || (i == 20) || (i == 30)) {
                continue;
            }

            // Добавляем тайл
            handSpecialArray[i]++;

            // Узнаем новое число шантен
            int newShanten = new Shanten(this.hand).calculate();

            // Проверяем уменьшил ли этот тайл кол-во шантен
            if (newShanten < shanten) {
                // Увеличиваем ту ячейку которой равен тайл(0, 1, 0...)
                upgradeTiles[i]++;
            }
            // Убираем добавленный тайл и переходим к следуюшему
            handSpecialArray[i]--;
        }
        return upgradeTiles;
    }

    public ArrayList<DiscardTile> getDiscardTiles() {
        return this.discardTiles;
    }

    private boolean isNotNull(int[] temp) {
        for (int data : temp) {
            if (data != 0) {
                return true;
            }
        }
        return false;
    }


}
