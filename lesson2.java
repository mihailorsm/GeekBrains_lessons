package com.company;

public class GeekLesson2 {
    public static void main(String[] args) {
    printArray(zero_or_one());
    printArray(creat_array());
    printArray(less_X2());
    printMatrix(matrix());
    min_and_max();
    System.out.println(checkBalance(new int[] {1, 1, 1, 2 }));
    printArray(movePosition(new int[]{ 0, 0, 0, 0, 1, 0, 0, 0, 0},-5));
   // System.out.print(-6%5);
    }

//1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
// С помощью цикла и условия заменить 0 на 1, 1 на 0;

    private static int[] zero_or_one() {
        int array[] = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++)
            switch (array[i]) {
                case 0:
                    array[i] = 1;
                    break;
                case 1:
                    array[i] = 0;
                    break;
            }
        return array;
    }

//2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;


    private static int[] creat_array(){
        int null_array[] = new int[8];
        for (int i=0; i < null_array.length; i++)
            null_array[i] = i*3;
        return null_array;
    }

//3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;

    private static int[] less_X2(){
        int mas[] = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i=0; i < mas.length; i++)
            if (mas[i] < 6)
                mas[i] = mas[i]*2;
        return mas;
    }

//4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и
// с помощью цикла(-ов) заполнить его диагональные элементы единицами;


    private static int[][] matrix(){
        int matrix[][] = new int[5][5];
        for (int i=0; i <= matrix.length - 1; i++)
            for (int j=0; j <= matrix.length - 1; j++) {
                if ((i == j) || (i+j == matrix.length-1))
                    matrix[i][j] = 1;
            }
            return matrix;
    }


//5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);


    private static void min_and_max(){
        int mas[] = { 1, 5, 3, 2, 11, 4, 5, 1, 4, 8, 9, 1 };
        int min = mas[0];
        int max = mas[0];
        for (int i =1; i < mas.length ; i++){
            if (mas[i] > max)
                max = mas[i];
            if (mas[i] < min)
                min = mas[i];
        }
        System.out.println("min = " + min + ", max = " + max );
    }


//6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true
// если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true,
// граница показана символами ||, эти символы в массив не входят.


    private static  boolean checkBalance (int mas[]){
        boolean balanced = false;
        int sum = 0;
        for (int i = 0; i < mas.length; i++)
            sum+=mas[i];
        if (sum % 2 !=0)
            return balanced;
        int left = mas[0];
        int right = sum - mas[0];
        int i = 1;
        do{
            if (left == right) {
                balanced = true;
                break;
            }
            left += mas[i];
            right -= mas[i];
            i++;
        }
        while (i < mas.length);
        return balanced;
    }
//7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
// при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.

    private static int[] movePosition(int mas[], int n) {
        if (n % mas.length != 0 )  //проверка на полный сдвиг до своего же места
            n = n % mas.length;

        switch(n) {
            case 0: // если 0, двигать не нужно
                break;
            default:
                if (n > 0)          // сдвиг вправо на n позиций
                    for (int i = 0; i < n; i++) {
                        int a = mas[mas.length-1];
                        for (int j = mas.length - 1; j > 0 ; j--)
                            mas[j] = mas[j-1];
                        mas[0] = a;
                    }
                    else            //сдвиг влево на n позиций
                        for (int i = 0; i < Math.abs(n); i++) {
                            int a = mas[0];
                            for (int j = 0; j < mas.length - 1; j++)
                                mas[j] = mas[j+1];
                            mas[mas.length-1] = a;
                        }
        }
        return mas;
    }

// Вывод массива


    private static void printArray(int array[]){
        for (int element : array)
            System.out.print(element + " ");
        System.out.println();
    }


// Вывод матрицы


    private static void printMatrix(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix.length; j++)
                System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
    }

}
