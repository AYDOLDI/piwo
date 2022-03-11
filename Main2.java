package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static int total;

    public static void main(String[] args) throws IOException {
        //File reading section
        List<String> text = new ArrayList<>();
        text = Files.readAllLines(Path.of("piworks.txt"));
        int[][] matrix = new int[text.size()][text.size()];

        //defining
        int column = 0, pathSum = 0;
        //split the lines word by word, then put them into matrix
        for (int i = 0; i < text.size(); i++) {
            String[] wordsInLine = text.get(i).split(" ");
            for (int j = 0; j < wordsInLine.length; j++) {
                matrix[i][column++] = Integer.parseInt(wordsInLine[j]);
            }
            column = 0;
        }
        
        //main function
        pathResult(matrix, pathSum, 0, 0);
        //display
        System.out.println("Maximum calculated path: " + total);

    }

    //checks the number if it is prime or not
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    //by using matrix tries the all possible path thanks to recursion
    public static void pathResult(int[][] matrix, int pathSum, int row, int column) {
        if (isPrime(matrix[row][column]) == false) {
            pathSum += matrix[row][column];
            //if we arrived to the last row of matrix, recursion does not go further
            if (row == matrix.length - 1) {
                if (pathSum > total) {
                    total = pathSum;
                }
            }
            //checks the left diagonal, right diagonal and bottom elements to find a path
            else {
                pathResult(matrix, pathSum, row + 1, column);
                pathResult(matrix, pathSum, row + 1, column + 1);
                if (column != 0)
                    pathResult(matrix, pathSum, row + 1, column - 1);
            }
        }
        //if element is prime, recursion does not go further
        else if (pathSum > total) {
            total = pathSum;
        }
    }
}
