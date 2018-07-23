package com.company;

import java.util.Random;
import java.util.Scanner;
// javadoc ??
public class GeekLesson4 {

    public static int SIZE = 3;
    public static int WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        mapInit();
        printMap();
        while (true) {
            yourTurn();
            printMap();
            if (isWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;

            }
            aiTurn();
            printMap();
            if (isWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void mapInit() {
        map = new char[SIZE + 1][SIZE + 1];
        for (int i = 1; i < SIZE + 1; i++) {
            map[0][i] = Character.forDigit(i, 10);
            map[i][0] = map[0][i];
        }
        for (int i = 1; i < SIZE + 1; i++)
            for (int j = 1; j < SIZE + 1; j++)
                map[i][j] = DOT_EMPTY;
    }

    public static void printMap() {
        for (int i = 0; i < SIZE + 1; i++) {
            for (int j = 0; j < SIZE + 1; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void yourTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scan.nextInt();
            y = scan.nextInt();
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;

    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE+1);
            y = rand.nextInt(SIZE+1);
        } while (!isCellValid(x, y));
        System.out.println("Ваш противник походил в точку " + x + " " + y);
        map[y][x] = DOT_O;

    }

    public static boolean isCellValid(int x, int y) {
        if (x < 1 || x >= SIZE+1 || y < 1 || y >= SIZE+1) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;

    }

    public static boolean isMapFull() {
        for (int i = 1; i < SIZE+1; i++) {
            for (int j = 1; j < SIZE+1; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean isWin(char symb){
        boolean flag = false;
        for (int i = 1; i < SIZE+1; i++) {
            for (int j = 1; j < SIZE + 1; j++) {
                if (!(map[i][j] == symb))
                    break;
            }
            flag = true;
            if (flag)
                break;
        }
        for (int i = 1; i < SIZE+1; i++) {
            if (flag)
                break;
            for (int j = 1; j < SIZE + 1; j++) {
                if (!(map[j][i] == symb))
                    break;
            }
            flag = true;
            if (flag)
                break;
        }
        flag = true;
        for (int i = 1; i < SIZE+1; i++) {

                if (!(map[i][i] == symb)) {
                    flag = false;
                    break;
                }
        }
        flag = true;
        for (int i = 1; i < SIZE+1; i++) {

                if (!(map[i][SIZE+1-i] == symb)){
                    flag = false;
                    break;
                }
                }
        return flag;
    }

}