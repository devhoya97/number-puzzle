package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Puzzle {
    private static final int PUZZLE_LENGTH = 8;
    public static void main(String[] args) {
        System.out.println("간단 숫자 퍼즐");
    }

    public static void playTurns() {
        List<Integer> randomNumbers = createRandomNumbers();
        List<Integer> answer = new ArrayList<>(randomNumbers);
        Collections.sort(answer);
        int turn = 0;
        while(!randomNumbers.equals(answer)) {
            turn++;
            playTurn(turn);
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

    public static void playTurn(int turn) {

    }
}

