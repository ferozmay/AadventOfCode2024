package day02;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day02 {

    public static boolean isSafe(List<Integer> levels) {

        boolean increasing = levels.get(1) > levels.get(0);
        boolean valid = true;

        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - levels.get(i - 1);
            // Check if the difference is within the allowed range
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                valid = false;
                break;
            }
            // Check if the sequence is consistently increasing or decreasing
            if ((diff > 0) != increasing) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private static boolean canBeMadeSafe(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> modifiedLevels = new ArrayList<>(levels);
            modifiedLevels.remove(i);
            if (isSafe(modifiedLevels))
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        String fileName = "input.txt";
        List<List<Integer>> reports = new ArrayList<>();

        // Read the file and parse each line into a list of integers
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            List<Integer> levels = new ArrayList<>();
            for (String part : parts) {
                levels.add(Integer.parseInt(part));
            }
            reports.add(levels);
        }
        scanner.close();

        int safe1 = 0;
        int safe2 = 0;
        for (List<Integer> report : reports) {
            if (isSafe(report))
                safe1++;
            else {
                if (canBeMadeSafe(report))
                    safe2++;
            }
        }

        int part1 = safe1;
        int part2 = safe1 + safe2;
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);

    }

}
