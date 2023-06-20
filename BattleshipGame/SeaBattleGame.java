


import java.util.Scanner;

public class SeaBattleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameBoard gameBoard = new GameBoard();

        while (true) {
            System.out.println("1. Новая игра");
            System.out.println("2. Результаты");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    gameBoard.startGame();
                    break;
                case 2:
                    gameBoard.showTopScores();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
        }
    }
}

class GameBoard {
    private static final int BOARD_SIZE = 8;
    private static final int NUM_SHIPS = 3;
    private static final int MAX_GUESS_TIME = 15;

    private char[][] board;
    private int numShips;
    private long startTime;

    public GameBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        numShips = NUM_SHIPS;
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void placeShips() {
        board[0][0] = 'S'; 
        board[2][4] = 'S'; 
        board[3][4] = 'S';
        board[5][2] = 'S'; 
        board[5][3] = 'S';
    }

    private void printBoard() {
        System.out.println("   A B C D E F G H");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void updateBoard(int row, int col, boolean isHit) {
        if (isHit) {
            board[row][col] = 'U';
            markAdjacentCells(row, col, 'o');
            numShips--;
            if (numShips == 0) {
                long endTime = System.currentTimeMillis();
                double elapsedTime = (endTime - startTime) / 1000.0;
                System.out.println("Поздравляем! Вы уничтожили все корабли!");
                System.out.println("Время игры: " + elapsedTime + " секунд");
                return;
            }
        } else {
            board[row][col] = 'o';
        }

        System.out.println("Промах!");
    }

    private void markAdjacentCells(int row, int col, char mark) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidCell(i, j) && board[i][j] != 'U') {
                    board[i][j] = mark;
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();
        placeShips();
        startTime = System.currentTimeMillis();

        while (true) {
            printBoard();
            System.out.print("Куда стреляем: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() != 2) {
                System.out.println("Некорректный формат ввода. Попробуйте снова.");
                continue;
            }

            int col = input.charAt(0) - 'A';
            int row = input.charAt(1) - '1';

            if (!isValidCell(row, col)) {
                System.out.println("Некорректные координаты. Попробуйте снова.");
                continue;
            }

            if (board[row][col] == 'U' || board[row][col] == 'o') {
                System.out.println("Вы уже стреляли в эту ячейку. Попробуйте снова.");
                continue;
            }

            boolean isHit = board[row][col] == 'S';
            updateBoard(row, col, isHit);

            if (numShips == 0) {
                break;
            }
        }
    }

    public void showTopScores() {
        System.out.println("Топ 3 самых быстрых игр:");
        System.out.println("1. 10 секунд");
        System.out.println("2. 15 секунд");
        System.out.println("3. 20 секунд");
    }
}
