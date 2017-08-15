package com.simplecalculator;

/**
 * Класс считывает строку и переводит её в такой формат:
 * 0: разделитель;
 * 1-9: тайлы ман;
 * 10: разделитель;
 * 11-19: тайлы пин;
 * 20: разделитель;
 * 21-29: тайлы соу;
 * 30: разделитель;
 * 31: 東 (восток)
 * 32: 南 (юг)
 * 33: 西 (запад)
 * 34: 北 (север)
 * 35: 白 (белый дракон)
 * 36: 発 (зелёный дракон)
 * 37: 中 (красный дракон)
 */
public class Reader {
    private int[] hand;
    private String stringHand;

    public Reader(String stringHand) {
        this.stringHand = stringHand;
        convertStringToSpecialArray();
    }

    /**
     * Конвертируем строку виду 123m123p123s11z
     * в массив [0,2,1,1...]
     */
    public void convertStringToSpecialArray() {
        String[] suites = foundTilesInSuits();

        int[] man = createArray(suites[0]);
        int[] pin = createArray(suites[1]);
        int[] sou = createArray(suites[2]);
        int[] hon = createArray(suites[3]);

        int[] allSuitsTogether = new int[38];

        for (int i = 0, j = 0; i < 38; i++, j++) {

            if (j == 10) {
                j = 0;
            }

            if (i < 10) {
                allSuitsTogether[i] = man[j];
            }
            if ((i >= 10) && (i < 20)) {
                allSuitsTogether[i] = pin[j];
            }
            if ((i >= 20) && (i < 30)) {
                allSuitsTogether[i] = sou[j];
            }
            if ((i >= 30) && (i < 38)) {
                allSuitsTogether[i] = hon[j];
            }
        }
        hand = allSuitsTogether;
    }

    /**
     * Делим строку на части по мастям
     * @return массив состоящий из 4 частей(по мастям)
     */
    public String[] foundTilesInSuits() {
        // Изначальный размер строки 0
        String m = "", s = "", p = "", z = "";

        if (this.stringHand.contains("m")) {
            m = this.stringHand.substring(m.length() + p.length() + s.length(),
                    this.stringHand.indexOf("m"));
        }

        if (this.stringHand.contains("p")) {
            p = this.stringHand.substring(m.length() + p.length() + s.length(),
                    this.stringHand.indexOf("p"));
        }

        if (this.stringHand.contains("s")) {
            s = this.stringHand.substring(m.length() + p.length() + s.length(),
                    this.stringHand.indexOf("s"));
        }

        if (this.stringHand.contains("z")) {
            z = this.stringHand.substring(m.length() + p.length() + s.length(),
                    this.stringHand.indexOf("z"));
        }

        // После разделения на масти остаются буквы с помощью этого мы их убраем
        m = m.replaceAll("[a-z]", "");
        p = p.replaceAll("[a-z]", "");
        s = s.replaceAll("[a-z]", "");
        z = z.replaceAll("[a-z]", "");

        String[] tilesInSuits = new String[4];
        tilesInSuits[0] = m;
        tilesInSuits[1] = p;
        tilesInSuits[2] = s;
        tilesInSuits[3] = z;

        return tilesInSuits;
    }

    // Создаем массив где значение ячейки - это кол-во тайлов в ней(макс 4)
    // Например {0, 3, 1} - 222ман 3ман
    public int[] createArray(String tiles) {

        // Разбиваем строку тайлов на символы
        char[] charArray = tiles.toCharArray();

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
                if (array[tile] < 4) {
                    // То увеличиваем кол-во этого тайла на 1
                    array[tile]++;
                }
            } else {
                // Иначе отмечаем что такого тайла 1 штука
                array[tile] = 1;
            }

            // Присваем записаный тайл во временную переменную
            temp = Integer.parseInt(Character.toString(charArray[i]));
        }
        return array;
    }

    public int[] getHand() {
        return hand;
    }
}
