package com.company;

/**
 * Java. Level 1. Lesson 8. Homework
 * Tic-tac-toe
 *
 * @autor Mihail Nuzhdi
 * @version dated Aug 6, 2018
 * @link https://github.com/mihailorsm
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Field {
    private static int FIELD_SIZE ;
    private static int CELL_SIZE ;
    private static final char DOT_EMPTY = '•';
    private static final char HUMAN_DOT = 'X';
    private static final char AI_DOT = 'O';
    public static char[][] map;
    private final String MSG_DRAW = "It's DRAW !";
    private final String MSG_WIN = "You WIN !";
    private final String MSG_LOSE = "You LOSE :(";
    private String gameOverMsg;


    public Field(int FIELD_SIZE, int CELL_SIZE){
        this.FIELD_SIZE = FIELD_SIZE;
        this.CELL_SIZE = CELL_SIZE;
        map = new char[FIELD_SIZE][FIELD_SIZE];
        init();
    }

    public void init(){
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                map[i][j] = DOT_EMPTY;
        gameOverMsg = null;
    }


    public int getSize(){return FIELD_SIZE;}

    public void setDot(int x, int y, char dot){
        map[x][y] = dot;

        if (checkWin(HUMAN_DOT))
            gameOverMsg = MSG_WIN;
        else if (checkWin(AI_DOT))
            gameOverMsg = MSG_LOSE;
        else if (isMapFull())
            gameOverMsg = MSG_DRAW;
    }

    public boolean isGameOver(){
        return gameOverMsg != null;
    }

    public boolean isMapFull(){
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    public boolean checkWin(char dot){
        // checking horizontals / verticals
        for (int i = 0; i < FIELD_SIZE; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                    (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
                return true;
        // checking diagonals
        if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
                (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
            return true;
        return false;
    }

    public boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
        if (map[x][y] == DOT_EMPTY) return true;
        return false;
    }


    public String getGameOverMsg(){return gameOverMsg;}

    public void setGameOverMsg(String s){
        this.gameOverMsg = null;
    }

    public char getAIDot(){
        return AI_DOT;
    }

    public char getHumanDot(){
        return HUMAN_DOT ;
    }

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        for (int i = 0; i < FIELD_SIZE; i++) {
            g.drawLine(0, i*CELL_SIZE, FIELD_SIZE*CELL_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, FIELD_SIZE*CELL_SIZE);
        }
        Graphics2D g2 = (Graphics2D) g; // use Graphics2D
        g2.setStroke(new BasicStroke(5));
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (map[x][y] == HUMAN_DOT) {
                    g.setColor(Color.blue);
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                }
                if (map[x][y] == AI_DOT) {
                    g.setColor(Color.red);
                    g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                }
            }
        }
    }

    public static char getEmptyDot() {
        return DOT_EMPTY;
    }
}
