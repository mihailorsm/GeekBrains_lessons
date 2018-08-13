package com.company.lvl2;





public class lesson2 {

    private static final class RowMismatchException extends RuntimeException {
        RowMismatchException(String message) {
            super("Rows exception: " + message);
        }
    }

    private static final class ColumnMismatchException extends RuntimeException {
        ColumnMismatchException(String message) {
            super("Columns exception: " + message);
        }
    }

    private static final String MY_STRING = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0";

    private static final int MATRIX_SIZE = 4;


    public static void main(String[] args) {
        try {
            System.out.println(calcMatrix(stringToMatrix("1 2 1 2 4\n2 3 2 u\n5 6 7 1\n3 3 1 0")));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String[][] stringToMatrix(String s){
        String[][] result = new String[MATRIX_SIZE][MATRIX_SIZE];
        int k = 0;
        String rows[] = s.split("\n");
        if (rows.length != MATRIX_SIZE)
            throw new RowMismatchException(rows.length + ":\n" + s);

        for (int i = 0; i < result.length; i++) {
            result[i] = rows[i].split(" ");
            if (result[i].length != MATRIX_SIZE)
                throw new ColumnMismatchException(result[i].length + ":\n" + s);
        }
        return result;
    }

        private static float calcMatrix(String[][] matrix) {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    sum += Integer.parseInt(matrix[i][j]);
                }
            }
            return sum / 2;
        }
}
