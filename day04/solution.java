package day04;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class solution {
    private static char[][] getLetterGrid(String filename) throws IOException {

        Scanner scanner = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        int rows = 0;

        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
            rows++;
        }
        scanner.close();

        String[] lines = sb.toString().split("\n");
        int cols = lines[0].length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines[i].toCharArray();
        }

        return grid;
    }

    private static boolean isWordAt(char[][] grid, String word, int startRow, int startCol, int[] direction) {
        int rows = grid.length;
        int cols = grid[0].length;
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            int newRow = startRow + i * direction[0];
            int newCol = startCol + i * direction[1];

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            if (grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static int countWordOccurrences(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Directions: [row offset, column offset]
        int[][] directions = {
                { 0, 1 }, // Right
                { 1, 0 }, // Down
                { 1, 1 }, // Down-right (Diagonal)
                { 1, -1 }, // Down-left (Diagonal)
                { 0, -1 }, // Left
                { -1, 0 }, // Up
                { -1, -1 }, // Up-left (Diagonal)
                { -1, 1 } // Up-right (Diagonal)
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] direction : directions) {
                    if (isWordAt(grid, word, row, col, direction)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        String filename = "input.txt";
        char[][] grid = getLetterGrid(filename);

        String target = "XMAS";
        int part1 = countWordOccurrences(grid, target);

        System.out.println("Part 1: " + part1);
    }
}
