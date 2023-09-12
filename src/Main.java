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
            System.out.println("Please enter how many rows you would like");
            System.out.print("Rows: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            int rows = scanner.nextInt();

            System.out.println("Please enter how many columns you would like");
            System.out.print("Columns: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            int cols = scanner.nextInt();

            System.out.println("Please enter how many mines you would like");
            System.out.print("Mines: ");
            // not working
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number that is less than the number of cells within the Board: ");
                scanner.next();
            }

            int numOfMines = scanner.nextInt();
            System.out.println();
            if (numOfMines >= (rows * cols)) {
                System.out.println("You've entered a number greater than the max allowed"); // change this print to something more accurate
                System.out.println("Setting the number of mines to 1 less than the max");
                System.out.println("Good Luck on trying to win :)");
            }

            grid = new Grid(rows, cols, numOfMines);
            grid.printBoard();

            while(!grid.isGameOver()) {
                System.out.printf("Please enter a row from 0 to %d\n", rows - 1);
                System.out.print("Row: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Please input a number: ");
                    scanner.next();
                }
                int row = scanner.nextInt();

                System.out.printf("Please enter a Column from 0 to %d\n", cols - 1);
                System.out.print("Column: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Please input a number: ");
                    scanner.next();
                }
                int col = scanner.nextInt();

                grid.playerMove(row, col);
                grid.printBoard();

            }

            System.out.println("Would you like to play again?");
            System.out.println("(Y/N): ");
            while (!scanner.hasNext(Pattern.compile("Y|N"))) {
                System.out.print("Please input a Y/N: ");
                scanner.next();
            }
            String playAgainInput = scanner.next();
            isPlaying = playAgainInput.equals("Y");
        }
        System.out.println("Thanks for playing :)");
        scanner.close();
    }
}