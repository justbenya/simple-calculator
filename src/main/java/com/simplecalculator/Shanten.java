package com.simplecalculator;

/**
 * Подсчет количества тайлов которые надо заменить,
 * чтобы дойти до готовой руки.
 */
public class Shanten {

    private int[] hand;
    private int sets;  // Количество сетов
    private int forms; // Количество форм
    private int pairs; // Количество пар
    private int shantenNormalHand; 
    
    public Shanten(Hand hand) {
        this.hand = hand.getHand();
    }
    
    public int calculate() {
        int normal = normal();
        int thirteenOrphans = thirteenOrphans();
        int sevenPairs = sevenPairs();
        
        return Math.min(sevenPairs, Math.min(thirteenOrphans, normal));
    }
    
    private int thirteenOrphans() {
        int kokushiPair = 0;
        int shanten = 13;
        
        // 1 и 9
        for (int i = 0; i < 30; i++) {
           
            if ((i % 10 == 1)|| (i % 10 == 9)) {
                if (hand[i] > 0) 
                    shanten--;
                
                if ((hand[i] >= 2) && (kokushiPair == 0)) 
                    kokushiPair = 1;
            }
        } 

        // достоинства
        for (int i = 31; i < 38; i++) {
            if (hand[i] > 0) {
                
                shanten--;
                
                if ((hand[i] >= 2) && (kokushiPair == 0)) {
                    kokushiPair = 1;
                }
            }
        }

        // Если есть одна необходимая пара - уменьшим шантен
        shanten -= kokushiPair;

        return shanten;
    }
    
    private int sevenPairs() {
        int types = 0;
        int pairs = 0;
        
        for (int i = 0; i < 38; i++) {
            if (hand[i] >= 2)
                pairs++;
        }
        
        for (int i = 0; i < 38; i++) {
            if (hand[i] > 0) 
                types++;           
        }
        
        int shanten = 6 - pairs;
        
        // 4 одинаковых тайла - не 2 пары
        if (types < 7) {
            shanten = shanten + 7 - types;
        }
        
        return shanten;    
    }
    
    private int normal() {
        shantenNormalHand = 8;
        
        sets = 0; 
        forms = 0;
        pairs = 0;
        
        // Нам нужна одна пара, попробуем все тайлы в качестве её.
        for (int i = 0; i < 38; i++) {
            if (hand[i] >= 2) {
                pairs++;
                hand[i] -= 2;
                
                cutSet(1);
                
                hand[i] += 2;
                pairs--;
            }
        }
        
        // Не нашли пару, пробуем без нее посчитать руку
        cutSet(1);
        
        return shantenNormalHand;
    }

    private void cutSet(int index) {

        while ((index < 38) && (hand[index] == 0)) {
            index++;
        }

        if (index >= 38) {
            cutForm(1);
            return;
        }

        if (hand[index] >= 3) {
            sets++;
            hand[index] -= 3;

            cutSet(index);

            hand[index] += 3;
            sets--;
        }

        if ((index < 30) && (hand[index + 1] > 0) && (hand[index + 2] > 0)) {
            sets++;
            hand[index]--;
            hand[index + 1]--;
            hand[index + 2]--;

            cutSet(index);

            hand[index]++;
            hand[index + 1]++;
            hand[index + 2]++;
            sets--;
        }
        cutSet(index + 1);
    }

    private void cutForm(int index) {
        
        while ((index < 38) && (hand[index] == 0)) {
            index++;
        }

        // Если формы кончились то смотрим, что вышло
        if (index >= 38) {       
            int temp = 8 - 2 * sets - forms - pairs;  

            if (temp < shantenNormalHand) { 
                shantenNormalHand = temp; 
            }       
            return;
        }
        
        // Если форм недостаточно, то считам еще
        // Из-за лишних форм кол-во шантен не уменьшается
        if (sets + forms < 4) {
            
            // Считаем пары
            if (hand[index] == 2) {
                forms++;
                hand[index] -= 2;                
                
                cutForm(index);
                
                hand[index] += 2;
                forms--;
            }
            
            // Пенчан(Пример: к 1 2 ждем 3) и рянмен
            if ((index < 30) && (hand[index + 1] > 0)) {            
                forms++;
                hand[index]--;
                hand[index + 1]--;
                
                cutForm(index);
                               
                hand[index]++;
                hand[index + 1]++;
                forms--;
            }
            
            // Канчан (Пример: к 3 5 ждем 4 ) 
            if ((index < 30) && ((index % 10) <= 8)&& (hand[index + 2] > 0)) {  
                forms++;
                hand[index]--;
                hand[index + 2]--;
                
                cutForm(index);
                               
                hand[index]++;
                hand[index + 2]++;
                forms--;
            }       
        }
        cutForm(index + 1);
    }
}
