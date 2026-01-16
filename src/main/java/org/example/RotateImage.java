package org.example;

public class RotateImage {
    public void rotate(int[][] matrix) {

        for(int i = 0; i < matrix.length ; i++){
            for(int j= i+1; j< matrix[i].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0 ; i < matrix.length ; i++){
            int mid = matrix[0].length/2;
            int width = matrix[0].length;
            for(int j = 0 ; j < mid ; j++){
               int temp = matrix[i][j];
               matrix[i][j] = matrix[i][width - 1 - j];
               matrix[i][width - 1 - j] = temp;
            }
        }

        for(int i = 0; i < matrix.length ; i++){
            for(int j=0; j< matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new RotateImage().rotate(new int[][] {{1,2,3},{4,5,6},{7,8,9}});
    }
}
