package com.company;

/**
 * Java. Level 1. Lesson 8. Homework
 * Tic-tac-toe
 *
 * @autor Mihail Nuzhdi
 * @version dated Aug 6, 2018
 * @link https://github.com/mihailorsm
 */

import java.util.Random;

public class AI {
    Random random = new Random();
    private final char DOT;

    public AI(char ch){ DOT = ch;}


    public void turn (Field field){
        int x, y;
        for (x = 0; x < field.getSize(); x++)
            for (y = 0; y < field.getSize(); y++)
                if (field.isCellValid(x,y)){
                    field.setDot(x,y, field.getHumanDot());
                    if (field.checkWin(field.getHumanDot())){
                        field.setGameOverMsg(null);
                        field.setDot(x,y,DOT);
                        return;
                    }
                    field.setDot(x,y, field.getEmptyDot());

                }
        do {
            x = random.nextInt(field.getSize());
            y = random.nextInt(field.getSize());
        } while (!field.isCellValid(x, y));
        field.setDot(x,y, DOT);
    }

}
