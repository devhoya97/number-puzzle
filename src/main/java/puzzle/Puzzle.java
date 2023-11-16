package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Puzzle {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 8;
    private static final int NUMBERS_LENGTH = MAX_NUMBER - MIN_NUMBER + 1;
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
        List<Integer> randomNumbers = new ArrayList<>(NUMBERS_LENGTH);
        while(randomNumbers.size() < NUMBERS_LENGTH) {
            int randomNumber = random.nextInt(NUMBERS_LENGTH) + MIN_NUMBER;
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
        System.out.println("교환할 두 숫자를 입력>");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return parseInputToChangeTargets(input);
    }

    public static List<Integer> parseInputToChangeTargets(String input) {
        String[] splitInput = input.split(DELIMITER);
        if (splitInput.length != CHANGE_TARGETS_SIZE) {
            throw new IllegalArgumentException(INPUT_ERROR);
        }
        int firstChangeTarget = parseSplitInputToInteger(splitInput[0]);
        int secondChangeTarget = parseSplitInputToInteger(splitInput[1].trim());
        validateTargetRange(firstChangeTarget);
        validateTargetRange(secondChangeTarget);

        return List.of(firstChangeTarget, secondChangeTarget);
    }

    public static int parseSplitInputToInteger(String splitInput) {
        try {
            return Integer.parseInt(splitInput);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(INPUT_ERROR);
        }
    }

    public static void validateTargetRange(int changeTarget) {
        if (changeTarget < MIN_NUMBER || changeTarget > MAX_NUMBER) {
            throw new IllegalArgumentException(INPUT_ERROR);
        }
    }
}

