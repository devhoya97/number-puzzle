package puzzle;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Puzzle {
    private static final int ROW_LENGTH = 4;
    private static final int COLUMN_LENGTH = 4;
    private static final int BLANK_EXPRESSION = 16;
    private static final int OVER_INDEX_DEFAULT = -1;
    private static final int SHUFFLE_COUNT = 1000;
    private static final String INPUT_ERROR = "\n잘못 입력하셨습니다. 다시 입력해 주세요.\n";

    public static void main(String[] args) {
        List<List<Integer>> answer = createAnswer();
        List<List<Integer>> gameBoard = createAnswer();
        shuffleGameBoard(gameBoard);
        int turn = 1;
        while (!isAnswer(gameBoard, answer)) {
            printGameBoard(gameBoard, turn);
            tryUntilValidInput(gameBoard);
            turn++;
        }
        printAnswerMessage(gameBoard, turn);
    }

    private static void tryUntilValidInput(List<List<Integer>> gameBoard) {
        while (true) {
            try {
                int userNumber = getUserNumber();
                exchangeBlankAndTargetCandidate(gameBoard, userNumber);
                return;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
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

    private static void shuffleGameBoard(List<List<Integer>> gameBoard) {
        Random random = new Random();
        for (int count = 0; count < SHUFFLE_COUNT; count++) {
            int randomNumber = random.nextInt(BLANK_EXPRESSION - 1) + 1;
            try {
                exchangeBlankAndTargetCandidate(gameBoard, randomNumber);
            } catch (IllegalArgumentException illegalArgumentException) {
                // randomNumber가 공백과 인접한 숫자를 뽑지 못한 경우, 교환 없이 count만 증가시킨다.
            }
        }
    }

    private static void exchangeBlankAndTargetCandidate(List<List<Integer>> gameBoard, int targetCandidate) {
        int blankRowIndex= getBlankRowIndex(gameBoard);
        int blankColumnIndex = getBlankColumnIndex(gameBoard.get(blankRowIndex));
        if(exchangeBlankUpDirection(gameBoard, blankRowIndex, blankColumnIndex, targetCandidate)) {
            return;
        }
        if (exchangeBlankDownDirection(gameBoard, blankRowIndex, blankColumnIndex, targetCandidate)) {
            return;
        }
        if (exchangeBlankLeftDirection(gameBoard, blankRowIndex, blankColumnIndex, targetCandidate)) {
            return;
        }
        if (exchangeBlankRightDirection(gameBoard, blankRowIndex, blankColumnIndex, targetCandidate)) {
            return;
        }
        throw new IllegalArgumentException(INPUT_ERROR);
    }

    private static Integer getBlankRowIndex(List<List<Integer>> gameBoard) {
        for (int rowCount = 0; rowCount < ROW_LENGTH; rowCount++) {
            List<Integer> row = gameBoard.get(rowCount);
            if (row.contains(BLANK_EXPRESSION)) {
                return rowCount;
            }
        }
        throw new IllegalStateException("게임보드에서 공백을 찾을 수 없습니다.");
    }

    private static Integer getBlankColumnIndex(List<Integer> row) {
        return row.indexOf(BLANK_EXPRESSION);
    }

    private static boolean exchangeBlankUpDirection(List<List<Integer>> gameBoard, int blankRowIndex,
                                                    int blankColumnIndex, int targetCandidate) {
        List<Integer> upperRow = getRowOrDefault(gameBoard, blankRowIndex - 1);
        Integer target = upperRow.get(blankColumnIndex);
        if (target != targetCandidate) {
            return false;
        }
        upperRow.set(blankColumnIndex, BLANK_EXPRESSION);
        List<Integer> blankRow = gameBoard.get(blankRowIndex);
        blankRow.set(blankColumnIndex, targetCandidate);
        return true;
    }

    private static boolean exchangeBlankDownDirection(List<List<Integer>> gameBoard, int blankRowIndex,
                                                    int blankColumnIndex, int targetCandidate) {
        List<Integer> lowerRow = getRowOrDefault(gameBoard, blankRowIndex + 1);
        Integer target = lowerRow.get(blankColumnIndex);
        if (target != targetCandidate) {
            return false;
        }
        lowerRow.set(blankColumnIndex, BLANK_EXPRESSION);
        List<Integer> blankRow = gameBoard.get(blankRowIndex);
        blankRow.set(blankColumnIndex, targetCandidate);
        return true;
    }

    private static boolean exchangeBlankLeftDirection(List<List<Integer>> gameBoard, int blankRowIndex,
                                                      int blankColumnIndex, int targetCandidate) {
        List<Integer> blankRow = gameBoard.get(blankRowIndex);
        Integer target = getValueOrDefault(blankRow, blankColumnIndex - 1);
        if (target != targetCandidate) {
            return false;
        }
        blankRow.set(blankColumnIndex - 1, BLANK_EXPRESSION);
        blankRow.set(blankColumnIndex, targetCandidate);
        return true;
    }

    private static boolean exchangeBlankRightDirection(List<List<Integer>> gameBoard, int blankRowIndex,
                                                      int blankColumnIndex, int targetCandidate) {
        List<Integer> blankRow = gameBoard.get(blankRowIndex);
        Integer target = getValueOrDefault(blankRow, blankColumnIndex + 1);
        if (target != targetCandidate) {
            return false;
        }
        blankRow.set(blankColumnIndex + 1, BLANK_EXPRESSION);
        blankRow.set(blankColumnIndex, targetCandidate);
        return true;
    }

    private static List<Integer> getRowOrDefault(List<List<Integer>> gameBoard, int rowIndex) {
        if ((rowIndex < 0) || (gameBoard.size() <= rowIndex)) {
            List<Integer> defaults = new ArrayList<>(COLUMN_LENGTH);
            for (int count = 0; count < COLUMN_LENGTH; count++) {
                defaults.add(OVER_INDEX_DEFAULT);
            }
            return defaults;
        }
        return gameBoard.get(rowIndex);
    }

    private static Integer getValueOrDefault(List<Integer> row, int columnIndex) {
        if ((columnIndex < 0) || (row.size() <= columnIndex)) {
            return OVER_INDEX_DEFAULT;
        }
        return row.get(columnIndex);
    }

    private static int getUserNumber() {
        Scanner console = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("숫자 입력> ");
                return console.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(INPUT_ERROR);
                console.nextLine();
            }
        }
    }

    private static void printAnswerMessage(List<List<Integer>> gameBoard, int turn) {
        printGameBoard(gameBoard, turn);
        System.out.println();
        System.out.printf("축하합니다! %d턴만에 퍼즐을 완성했습니다!", turn);
    }

    private static void printGameBoard(List<List<Integer>> gameBoard, int turn) {
        System.out.println();
        System.out.println("Turn " + turn);
        for (List<Integer> row : gameBoard) {
            for (Integer number : row) {
                if (number == BLANK_EXPRESSION) {
                    System.out.print("[  ]");
                    continue;
                }
                System.out.printf("[%2d]", number);
            }
            System.out.println();
        }
    }

    private static boolean isAnswer(List<List<Integer>> gameBoard, List<List<Integer>> answer) {
        for (int rowIndex = 0; rowIndex < ROW_LENGTH; rowIndex++) {
            List<Integer> gameBoardRow = gameBoard.get(rowIndex);
            List<Integer> answerRow = answer.get(rowIndex);
            if (!gameBoardRow.equals(answerRow)) {
                return false;
            }
        }
        return true;
    }
}

