import java.util.Scanner;

import static java.lang.Integer.parseInt;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isPlaying = true;
        Grid grid;

        while (isPlaying) {
            System.out.println("Welcome to Minesweeper!");
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
            while (!scanner.hasNextInt() || scanner.nextInt() >= (rows * cols)) {
                System.out.print("Please input a number that is less than the number of cells within the Board: ");
                scanner.next();
            }
            int numOfMines = scanner.nextInt();
            System.out.println(numOfMines);

            grid = new Grid(rows, cols, numOfMines);

        }

        scanner.close();
    }
}