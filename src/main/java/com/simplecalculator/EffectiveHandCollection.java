
package com.simplecalculator;

import java.util.ArrayList;

public class EffectiveHandCollection {

    private int hand[];
    private final ArrayList<DiscardTile> discardTiles;

    public EffectiveHandCollection(int[] hand) {
        this.hand = hand;
        this.discardTiles = new ArrayList<>();
        calculatedUkeIre();
        // Отсортируем руку по кол-ву аутов
//        Collections.sort(wdList, Collections.reverseOrder(WhatDiscard.COMPARE_BY_OUTS));
    }
    
    private void calculatedUkeIre() {
        int shanten = new Shanten(this.hand).calculate();
        
        for (int tile = 0; tile < 38; tile++) {
             // Пропускаем все пустые(нулевые) ячейки
            if (this.hand[tile] == 0) {
                continue;
            }

            // Убираем 1 тайл 
            this.hand[tile]--;
            
            // Узнаем тайлы уменьшаюшие кол-во шантен
            int[] upgradeTiles = ArrayUpgradeTiles(shanten);
            
            // Если массив не состоит из нулей, т.е. есть тайлы
            if (isNotNull(upgradeTiles)) {
                discardTiles.add(new DiscardTile(tile, upgradeTiles, outs(upgradeTiles)));
            }
            
            // Восстанавливаем удаленный тайл
            this.hand[tile]++;
        }
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

    private int[] ArrayUpgradeTiles(int shanten) {
        int[] upgradeTiles = new int[38];
        
        // Теперь берем все 34 тайла
        for (int i = 0; i < 38; i++) {
            // Кроме этих разделителей 
            if ((i == 0) || (i == 10) || (i == 20) || (i == 30)) {
                continue;
            }

            // Добавляем тайл
            this.hand[i]++;

            // Узнаем новое число шантен
            int newShanten = new Shanten(this.hand).calculate();

            // Проверяем уменьшил ли этот тайл кол-во шантен
            if (newShanten < shanten) {
                // Увеличиваем ту ячейку которой равен тайл(0, 1, 0...)
                upgradeTiles[i]++; 
            }
            // Убираем добавленный тайл и переходим к следуюшему 
            this.hand[i]--;
        }
        return upgradeTiles;
    }
    
    private int outs(int[] specialArr) {
        
        int match = 0;
        int tilesCount = 0;
        
        for (int i = 0; i < hand.length; i++) {
            if (specialArr[i] > 0 ) {
                tilesCount++;
            }
            if ( (specialArr[i] > 0) & ( specialArr[i] <= hand[i]) ) {
                match = match + hand[i];
            } 
        } 
        return 4 * tilesCount - match; 
    }
}
