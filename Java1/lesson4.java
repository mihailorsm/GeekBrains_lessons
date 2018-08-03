/**
 * Java. Level 1. Lesson 4. Homework
 * Tic-tac-toe in console
 *
 * @autor Mihail Nuzhdi
 * @version dated Jul 24, 2018
 */

package com.company;


import java.util.Random;
import java.util.Scanner;


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
                System.out.println("Вы выиграли!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;

            }
            aiTurn();
            printMap();
            if (isWin(DOT_O)) {
                System.out.println("Вы проиграли :(");
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
        for (int i = 1; i <= SIZE; i++) {
            map[0][i] = Character.forDigit(i, 10);
            map[i][0] = map[0][i];
        }
        for (int i = 1; i <= SIZE; i++)
            for (int j = 1; j <= SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            for (int j = 0; j <= SIZE; j++) {
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
        for (x = 0; x <= SIZE; x++)
            for (y = 0; y <= SIZE; y++)
                if (isCellValid(x,y)){
                    map[y][x] = DOT_X;
                    if (isWin(DOT_X)){
                        map[y][x] = DOT_O;
                        return;
                    }
                    map[y][x] = DOT_EMPTY;
                }
        do {
            x = rand.nextInt(SIZE + 1);
            y = rand.nextInt(SIZE + 1);
        } while (!isCellValid(x, y));
        System.out.println("Ваш противник походил в точку " + x + " " + y);
        map[y][x] = DOT_O;

    }


    public static boolean isCellValid(int x, int y) {
        if (x < 1 || x >= SIZE + 1 || y < 1 || y >= SIZE + 1) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;

    }

    public static boolean isMapFull() {
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean isWin(char symb) {
        for (int y = 1; y <= SIZE; y++)
            for (int x = 1; x <= SIZE; x++)
                for (int dy = -1; dy < SIZE; dy++)
                    for (int dx = -1; dx < SIZE; dx++)
                        if (checkLine(x, y, dx, dy, symb) == SIZE)
                            return true;
        return false;
    }
    public static int checkLine (int x, int y, int dx, int dy, char dot){
        int count = 0;
        if (dx ==0 && dy ==0)
            return 0;
        for (int i = 1, xi = x, yi =y; i <= SIZE; i++, xi += dx, yi += dy)
            if (xi >= 1 && yi >= 1 && xi <= SIZE && yi <= SIZE && map[yi][xi] == dot)
                count++;
        return count;
    }
}