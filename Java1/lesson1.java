package com.company;

public class Main {

    public static void main(String[] args) {
        byte byt = 1;
        short shor = 2;
        int in = 22;
        long lon = 555;
        float flo = 3;
        double doub = 3.14;
        char chr = 'g';
        boolean bool = true;

        System.out.println(Solution(5,3,4,2));
        System.out.println(check(4,6));
        digit(14);
        hello("mihailo");
        isHighYear(2018);
        checkPositiveOrNegative(12);
// write your code here
    }


    private static int Solution(int a, int b, int c, int d) {
        int result = 0;
        try{
            result = a*(b+(c/d));
        }
        catch (Exception e) {
            System.out.println("");
        }
    return result ;
    }


    private static boolean check (int a, int b){
        return ((a+b>=10) && (a+b<=20)); // примерно также как и у меня, но лаконичнее

    }


    static void checkPositiveOrNegative(int a) {
        System.out.println(a + " is " + ((a < 0)? "Negitive" : "Positive"));
    } // было пропущено Оо


    private static boolean digit (int a){
            return (a<0);            // аналогично как выше
    }

    private static void hello (String name){
        System.out.println("Привет, " + name + "!");
    }

    private static void isHighYear (int year){
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
           System.out.println(year + " високосный");
        else
                System.out.println(year + " нисокосный");
    }
}
