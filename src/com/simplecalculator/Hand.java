package com.simplecalculator;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    // Переработать класс
    // Убрать лишнии метода во врапер
    // Дописать методы для перевод строки вида
    // 1m1m2m3m4m6m3p3p3p1s2s3s5s1z в массив который понимает программа.
    public static void main(String[] args) {
//        String strHand = "1p1p2p2p4p6p3p3p3p1s2s3s5s1z";
        String strHand = Wrapper.convertArrayHandToString(Wall.createRandHand());
        Hand hand1 = new Hand(strHand);
        System.out.println("Hand str = " + hand1.getStrHand() + " Hand length" + hand1.getStrHand().length()/2);
        System.out.println("Hand[] = " + Arrays.toString(hand1.getHand()));
//        System.out.println("Hand = " + Arrays.toString(Wrapper.convertStringHandToArrayHand(strHand)));
    }

//    public void RENAMEconvert(String strHand) {
//        int[] man = createArray(m);
//        int[] pin = createArray(p);
//        int[] sou = createArray(s);
//        int[] hon = createArray(z);
//
//        int[] array = new int[38];
//
//        for (int i = 0, j = 0; i < 38; i++, j++) {
//
//            if (j == 10) {
//                j = 0;
//            }
//
//            if (i < 10) {
//                array[i] = man[j];
//            }
//            if ((i >= 10) && (i < 20)) {
//                array[i] = pin[j];
//            }
//            if ((i >= 20) && (i < 30)) {
//                array[i] = sou[j];
//            }
//            if ((i >= 30) && (i < 38)) {
//                array[i] = hon[j];
//            }
//        }
//        hand = array;
//    }

    private String strHand;
    private int[] hand;

    public Hand(String strHand) {
        this.strHand = strHand;
        convert(strHand);
    }
    
    public Hand() {
    }

    public String getStrHand() {
        return strHand;
    }

    public int[] getHand() {
        return hand;
    }

    public int calculateShanten() {
        Shanten shanten = new Shanten(hand);
        return shanten.calculateShanten();
    }
    
    public ArrayList<WhatDiscard> calculatedUkeIre() {
        UkeIre ukeire = new UkeIre(hand);
        return ukeire.getUkeIre();
    }
    
    // Переводим строку в целочисленный массив вида {0,2,3,2,0}
    private void convert(String ch) {

        // Изначальный размер строки 0
        String m = "", s = "", p = "", z = "";

        // Индекс для букв ман, пин, соу и хонор.
        int indexMan = 0, indexPin = 0, indexSoy = 0, indexZ = 0;

        // Кол-во доп символов
        int count = 0;

        // Если у нас есть эта масть(маны, пины и т.д.) то
        if (ch.contains("m")) {
            // Узнаем первое вхождение буквы
            indexMan = ch.indexOf("m");

            // Вырезаем с нулевого символа до буквы
            m = ch.substring(m.length(), indexMan);

            // Вырезаем все буквы которые могли остаться
            m = m.replaceAll("[a-z]", "");
        }

        if (ch.contains("p")) {
            indexPin = ch.indexOf("p");

            // Если у нас есть уже маны то еще берем доп символ
            if (ch.contains("m")) {
                count = 1;
            }

            p = ch.substring(m.length() + count, indexPin);
            p = p.replaceAll("[a-z]", "");
        }

        if (ch.contains("s")) {
            indexSoy = ch.indexOf("s");

            if (ch.contains("m")) {
                count = 1;
            }

            s = ch.substring(m.length() + p.length() + count, indexSoy);
            s = s.replaceAll("[a-z]", "");
        }

        if (ch.contains("z")) {
            indexZ = ch.indexOf("z");

            if (ch.contains("m")) {
                count = 1;
            }
            if (ch.contains("p")) {
                count = 2;
            }
            if (ch.contains("s")) {
                count = 2;
            }

            z = ch.substring(m.length() + p.length() + s.length() + count, indexZ);
            z = z.replaceAll("[a-z]", "");
        }
        
        int[] man = createArray(m);
        int[] pin = createArray(p);
        int[] sou = createArray(s);
        int[] hon = createArray(z);

        int[] array = new int[38];

        for (int i = 0, j = 0; i < 38; i++, j++) {

            if (j == 10) {
                j = 0;
            }

            if (i < 10) {
                array[i] = man[j];
            }
            if ((i >= 10) && (i < 20)) {
                array[i] = pin[j];
            }
            if ((i >= 20) && (i < 30)) {
                array[i] = sou[j];
            }
            if ((i >= 30) && (i < 38)) {
                array[i] = hon[j];
            }
        }
        hand = array;
    }

    // Создаем массив где значение ячейки - это кол-во тайлов в ней(макс 4)
    // Например {0, 3, 1} - 222ман 3ман 
    public int[] createArray(String suit) {

        // Разбиваем строку тайлов на символы
        char[] charArray = suit.toCharArray();

        // Всего может быть 9 типов тайлов
        // 10, 20, 30 это пустой тайл
        int[] array = new int[10];

        // Хранит в себе предыдущий тайл
        int temp = 0;

        // Переводим символы в целочисленный массив
        for (int i = 0; i < charArray.length; i++) {

            // Получаем тайл из массива символов
            int tile = Integer.parseInt(Character.toString(charArray[i]));

            // Если есть уже такой тайл 
            if (tile == temp) {
                // То увеличиваем кол-во этого тайла на 1 
                array[tile]++;
            } else {
                // Иначе отмечаем что такого тайла 1 штука
                array[tile] = 1;
            }

            // Присваем записаный тайл во временную переменную
            temp = Integer.parseInt(Character.toString(charArray[i]));
        }
        return array;
    }
}
