package puzzle;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private static int ROW_LENGTH = 4;
    private static int COLUMN_LENGTH = 4;
    private static int BLANK_EXPRESSION = 16;
    private static int OVER_INDEX_DEFAULT = -1;

    public static void main(String[] args) {
        List<List<Integer>> gameBoard = createAnswer();
        int blankRow = getBlankRow(gameBoard);
        int blankColumn = getBlankColumn(gameBoard.get(blankRow));
        System.out.println(exchangeBlankUpDirection(gameBoard, blankRow, blankColumn, 12));
        System.out.println(gameBoard);
        blankRow = getBlankRow(gameBoard);
        blankColumn = getBlankColumn(gameBoard.get(blankRow));
        System.out.println(exchangeBlankDownDirection(gameBoard, blankRow, blankColumn, 12));
        System.out.println(gameBoard);

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

    }

    private static void exchangeBlankAndTarget(List<List<Integer>> gameBoard, int target) {
        int blankRow = getBlankRow(gameBoard);
        int blankColumn = getBlankColumn(gameBoard.get(blankRow));
        List<Integer> row = getListOrDefault(gameBoard, blankRow - 1);
        int upNumber = getValueOrDefault(row, blankColumn);
        if (upNumber == target) {
        }
    }

    private static Integer getBlankRow(List<List<Integer>> gameBoard) {
        for (int rowCount = 0; rowCount < ROW_LENGTH; rowCount++) {
            List<Integer> row = gameBoard.get(rowCount);
            if (row.contains(BLANK_EXPRESSION)) {
                return rowCount;
            }
        }
        throw new IllegalStateException("게임보드에서 공백을 찾을 수 없습니다.");
    }

    private static Integer getBlankColumn(List<Integer> row) {
        return row.indexOf(BLANK_EXPRESSION);
    }

    private static boolean exchangeBlankUpDirection(List<List<Integer>> gameBoard, int blankRowIndex,
                                                    int blankColumnIndex, int target) {
        List<Integer> upperRow = getListOrDefault(gameBoard, blankRowIndex - 1);
        Integer targetCandidate = upperRow.get(blankColumnIndex);
        if (targetCandidate != target) {
            return false;
        }
        upperRow.set(blankColumnIndex, BLANK_EXPRESSION);
        List<Integer> blankRow = gameBoard.get(blankRowIndex);
        blankRow.set(blankColumnIndex, target);
        return true;
    }

    private static boolean exchangeBlankDownDirection(List<List<Integer>> gameBoard, int blankRowIndex,
                                                    int blankColumnIndex, int target) {
        List<Integer> lowerRow = getListOrDefault(gameBoard, blankRowIndex + 1);
        Integer targetCandidate = lowerRow.get(blankColumnIndex);
        if (targetCandidate != target) {
            return false;
        }
        lowerRow.set(blankColumnIndex, BLANK_EXPRESSION);
        List<Integer> blankRow = gameBoard.get(blankRowIndex);
        blankRow.set(blankColumnIndex, target);
        return true;
    }

    private static Integer getValueOrDefault(List<Integer> row, int columnIndex) {
        if ((columnIndex < 0) || (row.size() <= columnIndex)) {
            return OVER_INDEX_DEFAULT;
        }
        return row.get(columnIndex);
    }

    private static List<Integer> getListOrDefault(List<List<Integer>> gameBoard, int rowIndex) {
        if ((rowIndex < 0) || (gameBoard.size() <= rowIndex)) {
            return List.of(OVER_INDEX_DEFAULT, OVER_INDEX_DEFAULT, OVER_INDEX_DEFAULT, OVER_INDEX_DEFAULT);
        }
        return gameBoard.get(rowIndex);
    }

    private static void printGameBoard(List<List<Integer>> gameBoard) {
        for (List<Integer> row : gameBoard) {
            for (Integer number : row) {
                if (number == 16) {
                    System.out.print("[  ]");
                    continue;
                }
                System.out.printf("[%2d]", number);
            }
            System.out.println();
        }
    }
}

