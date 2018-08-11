package com.company;

/**
 * Java. Level 1. Lesson 8. Homework
 * Tic-tac-toe
 *
 * @autor Mihail Nuzhdi
 * @version dated Aug 6, 2018
 * @link https://github.com/mihailorsm
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {


    final String TITLE_OF_PROGRAM = "Tic Tac Toe";
    final int WINDOW_SIZE = 330;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";

    Panel panel = new Panel();
    Field field = new Field(FIELD_SIZE, CELL_SIZE);
    Human human = new Human(field.getHumanDot());
    AI ai = new AI(field.getAIDot());

    public static void main(String[] args) {
        new TicTacToe();
    }

    public TicTacToe(){
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE+WINDOW_DX, WINDOW_SIZE+WINDOW_DY);
        setLocationRelativeTo(null);
        setResizable(false);

        panel.setBackground(Color.white);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                human.turn(e.getX()/CELL_SIZE, e.getY()/CELL_SIZE, field, ai);
                panel.repaint();
                if (field.isGameOver())
                    JOptionPane.showMessageDialog(
                        TicTacToe.this, field.getGameOverMsg());
            }
        } );
        JButton init = new JButton(BTN_INIT);

        init.addActionListener(e ->{
            field.init();
            panel.repaint();
        });

        JButton exit = new JButton(BTN_EXIT);

        exit.addActionListener(e -> System.exit(0));

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout());
        panelBtn.add(init);
        panelBtn.add(exit);

        add(panelBtn, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

public class  Panel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        field.paint(g);
    }
}
}
