package com.company;

public class Recursion {

    public static void main(String[] args) {
        int result = pow(2,10);
        int mas[] = {1, 2, 3, 3};
        int size = mas.length;

        System.out.println(result);
        result = binaryPow(2,9);
        System.out.println(result);

    }

    public static int pow(int value, int lvl){
        if (lvl == 1)
            return value;
        return value*pow(value, lvl-1);
    }

    public static int binaryPow(int value, int lvl){
        if (lvl == 0)
            return 1;
        if (lvl % 2 == 1)
            return binaryPow (value, lvl-1) * value;
        else {
            int b = binaryPow (value, lvl/2);
            return b * b;
        }
    }
}
