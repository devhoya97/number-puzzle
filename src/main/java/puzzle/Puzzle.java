package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Puzzle {
    private static int ROW_LENGTH = 4;
    private static int COLUMN_LENGTH = 4;

    public static void main(String[] args) {
        
    }

    private static List<List<Integer>> createAnswer() {
        List<List<Integer>> answer = new ArrayList<>();
        for (int rowCount = 0; rowCount < ROW_LENGTH; rowCount++) {
            List<Integer> row = new ArrayList<>(COLUMN_LENGTH);
            for (int columnCount = 0; columnCount < COLUMN_LENGTH; columnCount++) {
                row.add((rowCount * COLUMN_LENGTH) + columnCount + 1);
            }
            answer.add(row);
        }
        return answer;
    }
}

