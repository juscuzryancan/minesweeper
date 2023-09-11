public class Grid {
    private int rows;
    private int columns;
    private int numOfMines;
    private Cell[][] board;


    private boolean isGameOver;

    public Grid(int rows, int cols, int numOfMines) {
        this.rows = rows;
        this.columns = cols;
        this.numOfMines = numOfMines;
        initializeBoard(rows, cols);
        generateMines();
        calculateAdjacentNumberOfMines();
    }

    private void generateMines() {
        int currentNumberOfMines = 0;
        boolean isMaxedOut = false;
        while(!isMaxedOut) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if(Math.random() <= 0.2 && !board[i][j].isMine()) {
                        board[i][j].setMine(true);
                        currentNumberOfMines++;
                    }
                    if (currentNumberOfMines == numOfMines) {
                        isMaxedOut = true;
                        break;
                    }
                }
                if (isMaxedOut) break;
            }
        }
    }

    private void calculateAdjacentNumberOfMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int sumOfMines = 0;
                for(int xOff = -1; xOff <= 1; xOff++) {
                    for(int yOff = -1; yOff <= 1; yOff++) {
                        if(xOff == 0 && yOff == 0) continue;
                        sumOfMines += checkForMine(i + xOff, j + yOff);
                    }
                }
                board[i][j].setNumOfAdjacentMines(sumOfMines);
            }
        }
    }

    private int checkForMine(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= columns || !board[row][col].isMine()) return 0;
        return 1;
    }

    private void initializeBoard(int rows, int cols) {
        board = new Cell[rows][columns];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)  {
                board[i][j] = new Cell();
            }
        }
    }


    //the dfsing happens
    public void check(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= columns || board[x][y].isMine()) return;
        board[x][y].setChecked(true);
        if(board[x][y].getNumOfAdjacentMines() > 0) return;

        for(int xOff = -1; xOff <= 1; xOff++) {
            for(int yOff = -1; yOff <= 1; yOff++) {
                if(xOff == 0 && yOff == 0) continue;
                check(x + xOff, y + yOff);
            }
        }
    }

    public void printBoard()  {
        System.out.println("Printing the Board: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].isChecked()) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", numOfMines=" + numOfMines +
                '}';
    }
}
