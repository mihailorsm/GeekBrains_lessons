package com.company;

import java.util.Random;
import java.util.Scanner;

public class GeekLesson3 {

    //Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3
//попытки угадать это число. При каждой попытке компьютер должен сообщить больше ли
//указанное пользователем число, чем загаданное, или меньше. После победы или проигрыша
//выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    public static void main(String[] args) {
        playGame(makeNumber());

    }

    private static int makeNumber() {      // метод, который генерирует число от 0 до 9
        int number;
        Random rand = new Random();
        number = rand.nextInt(10);
        return number;
    }

    private static void playGame(int number) {
        Scanner scan = new Scanner(System.in);
        int shoot = 3;                             // начальное количество попыток
        boolean flag = false;                      // флаг или сигнал о том, что число угадали
        System.out.println("Я загадал число, у тебя 3 попытки");
        do {
            int trying = scan.nextInt();           // считываем число
            if (trying == number) {                // сравниваем с загаданным
                flag = true;                       // если угадали, поднимаем флаг и выводим в консоль
                System.out.println("Вы угадали с " + (4-shoot) + " попытки. \nЗагаданное число " + number);
            }
                else{
                    shoot--;                       // если не угадали, снижаем кол-во попыток
                    if (trying > number)           // сравниваем с загаданным и даем пользователю подсказку
                        System.out.println("Загаданное число меньше. Осталось " + shoot + " попытки");
                    else
                        System.out.println("Загаданное число больше. Осталось " + shoot + " попытки");
                }
        }
        while (shoot > 0 && !flag);                // условие для работы в цикле; пока есть попытки и число не угадали
        if (!flag)
            System.out.println("Вы проиграли");
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");         //
        if (scan.nextInt() == 1)        //
            playGame(makeNumber());     //
    }
}