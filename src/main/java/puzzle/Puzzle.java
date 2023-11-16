package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Puzzle {
    private static final int PUZZLE_LENGTH = 8;
    private static final int CHANGE_TARGETS_SIZE = 2;
    private static final String DELIMITER = ",";
    private static final String INPUT_ERROR = "잘못 입력하셨습니다. 다시 입력해 주세요.";

    public static void main(String[] args) {

    }

    public static void playTurns() {
        System.out.println("간단 숫자 퍼즐");
        List<Integer> randomNumbers = createRandomNumbers();
        List<Integer> answer = new ArrayList<>(randomNumbers);
        Collections.sort(answer);
        int turn = 0;
        while(!randomNumbers.equals(answer)) {
            turn++;
            playTurn(randomNumbers, turn);
        }
        System.out.println("축하합니다! " + turn + "턴만에 퍼즐을 완성하셨습니다!");
    }

    public static List<Integer> createRandomNumbers() {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>(PUZZLE_LENGTH);
        while(randomNumbers.size() < PUZZLE_LENGTH) {
            int randomNumber = random.nextInt(PUZZLE_LENGTH) + 1;
            if (randomNumbers.contains(randomNumber)) {
                continue;
            }
            randomNumbers.add(randomNumber);
        }
        return randomNumbers;
    }

    public static void playTurn(List<Integer> randomNumbers, int turn) {
        System.out.println("Trun " + turn);
        System.out.println(randomNumbers);
        try {
            List<Integer> changeTargets = getChangeTargets();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }
    }

    public static List<Integer> getChangeTargets() {
        return null;
    }
}

