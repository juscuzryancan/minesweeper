import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isPlaying = true;
        Grid grid;
        System.out.println("Welcome to Minesweeper!");
        while (isPlaying) {
            System.out.println("Let's initialize the board");
            int rows = getRows(scanner);
            int cols = getCols(scanner);
            int numOfMines = getNumOfMines(scanner, rows, cols);
            grid = new Grid(rows, cols, numOfMines);
            grid.printBoard();
            while(!grid.isGameOver()) {
                playerTurn(rows, scanner, cols, grid);
            }
            isPlaying = confirmPlayAgain(scanner);
        }
        System.out.println("Thanks for playing :)");
        scanner.close();
    }

    private static boolean confirmPlayAgain(Scanner scanner) {
        boolean isPlaying;
        System.out.println("Would you like to play again?");
        System.out.println("(Y/N): ");
        while (!scanner.hasNext(Pattern.compile("Y|N"))) {
            System.out.print("Please input a Y/N: ");
            scanner.next();
        }
        String playAgainInput = scanner.next();
        isPlaying = playAgainInput.equals("Y");
        return isPlaying;
    }

    private static void playerTurn(int rows, Scanner scanner, int cols, Grid grid) {
        int row;
        do {
            System.out.printf("Please enter a row from 0 to %d\n", rows - 1);
            System.out.print("Row: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            row = scanner.nextInt();
        } while (row < 0 || row >= rows);

        int col;
        do {
            System.out.printf("Please enter a Column from 0 to %d\n", cols - 1);
            System.out.print("Column: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            col = scanner.nextInt();
        } while (col < 0 || col >= cols);

        grid.playerMove(row, col);
        grid.printBoard();
    }

    private static int getNumOfMines(Scanner scanner, int rows, int cols) {
        int numOfMines;
        do {
            System.out.println("Please enter how many mines you would like (Greater than 0 and Less than the number of cells on the board)");
            System.out.print("Mines: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number that is less than the number of cells within the Board: ");
                scanner.next();
            }
            numOfMines = scanner.nextInt();
        } while (numOfMines <= 0 || numOfMines >= (rows * cols));
        return numOfMines;
    }

    private static int getCols(Scanner scanner) {
        int cols;
        do {
            System.out.println("Please enter how many columns you would like (Greater than 0)");
            System.out.print("Columns: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            cols = scanner.nextInt();
        } while (cols <= 0);
        return cols;
    }

    private static int getRows(Scanner scanner) {
        int rows;
        do {
            System.out.println("Please enter how many rows you would like (Greater than 0)");
            System.out.print("Rows: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            rows = scanner.nextInt();
        } while (rows <= 0);
        return rows;
    }
}