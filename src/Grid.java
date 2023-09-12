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
                        sumOfMines += checkCellForMine(i + xOff, j + yOff);
                    }
                }
                board[i][j].setNumOfAdjacentMines(sumOfMines);
            }
        }
    }

    private int checkCellForMine(int row, int col) {
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

    public void playerMove(int row, int col) {
        //messing up due to the player move returning early with a mine
        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            System.out.println("The coordinates entered are out of bounds. Please Try Again");
            return;
        }
        if (board[row][col].isChecked()) {
            System.out.println("This cell has already been checked");
            return;
        }

        if (board[row][col].isMine()) {
            System.out.println("KABOOM!!! Oh no you stepped on a mine");
            this.revealAll();
            this.setGameOver(true);
            return;
        }

        board[row][col].setChecked(true);
        playerMoveHelper(row, col);
    }

    private void playerMoveHelper(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= columns || board[row][col].isMine() || board[row][col].isChecked()) {
            System.out.println("The coordinates entered are out of bounds. Please Try Again");
            return;
        }
        board[row][col].setChecked(true);
        if(board[row][col].getNumOfAdjacentMines() > 0) return;
        for(int xOff = -1; xOff <= 1; xOff++) {
            for(int yOff = -1; yOff <= 1; yOff++) {
                playerMoveHelper(row + xOff, col + yOff);
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

    public void revealAll() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                board[i][j].setChecked(true);
            }
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
