package com.company;

/**
 * Java. Level 1. Lesson 8. Homework
 * Tic-tac-toe
 *
 * @autor Mihail Nuzhdi
 * @version dated Aug 6, 2018
 * @link https://github.com/mihailorsm
 */


public class Human {
    private final char DOT;

   public Human(char ch){DOT = ch;}

   public void turn (int x, int y,Field field, AI ai){
        if (field.isCellValid(x, y)) {
            if (!field.isGameOver())
                field.setDot(x, y, DOT);
            if (!field.isGameOver())
                ai.turn((field));
        }
    }
}
