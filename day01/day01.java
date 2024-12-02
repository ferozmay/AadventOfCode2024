import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;

public class day01 {
    public static void main(String[] args) throws IOException {

        String fileName = "input.txt";
        List<Integer> leftElements = new ArrayList<>();
        List<Integer> rightElements = new ArrayList<>();

        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            // Read a line and split into parts
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");

            if (parts.length == 2) {
                // Parse and store the numbers in respective lists
                leftElements.add(Integer.parseInt(parts[0]));
                rightElements.add(Integer.parseInt(parts[1]));
            }
        }
        scanner.close();

        Collections.sort(leftElements);
        Collections.sort(rightElements);

        // Count the frequency of each number in the right list
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : rightElements) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Calculate the sum of absolute differences
        int totalDifference = 0;
        for (int i = 0; i < leftElements.size(); i++) {
            int difference = Math.abs(leftElements.get(i) - rightElements.get(i));
            totalDifference += difference;
        }

        // Calculate the similarity score
        int similarityScore = 0;
        for (int num : leftElements) {
            int frequency = frequencyMap.getOrDefault(num, 0);
            similarityScore += num * frequency;
        }

        int part1 = totalDifference;
        int part2 = similarityScore;
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }
}