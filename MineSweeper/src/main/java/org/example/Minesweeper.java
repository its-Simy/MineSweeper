package org.example;

public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int [][] calcNumbers;
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;
        this.calcNumbers = new int[rows][cols];

        // Call methods to initialize the board and place mines
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    //make private once done
    public void initializeBoard() {
        // TODO: Implement this method
        int r = getRows();
        int c = getCols();
        char gameboard[][] = new char[r][c];
        for(int i = 0; i < r;i++){
            for(int j = 0; j < c; j++){
                gameboard[i][j] = '*';
                setBoard(gameboard);
                System.out.print(getBoard(i,j));

            }
            System.out.println();
        }
        placeMines();


    }

    // Method to randomly place mines on the board
    private void placeMines() {
        // TODO: Implement this method
        System.out.println();
        System.out.println();

        //will create a board that is 1x1 bigger than the game board that is shown
       // boolean bombboard[][] = new boolean[getRows()][getCols()];
        int bombcounter = 0;
        while(bombcounter < getNumMines())
        {
            int randomrow = (int)(Math.random() * getRows())%10;
            int randomcol = (int)(Math.random() * getCols())%10;
            if(!(getMines(randomrow,randomcol))) {
                //sets the mine board at the exact row and col to true
                setMines(randomrow,randomcol,true);
                setCalcNumbers(randomrow,randomcol,9);
                bombcounter++;
            }

        }

        for(int i = 0; i < getRows();i++){
            for(int j = 0; j < getCols(); j++){
                System.out.print(getMines(i,j));
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

        calculateNumbers();

    }


    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        // TODO: Implement this method
        for (int rows = 0; rows < getRows(); rows++){
            for(int cols = 0; cols < getCols(); cols++){
                    //if it's a bomb, It would like to check all around it in order to calculate those numbers
                    //this would isolate the ones that arent near bombs and keep them at 0
                    if(getCalcNumbers(rows,cols) == 9){
                    int mineCounter = 0;
                    for(int i =-1; i <=1 ; i++){
                        for(int j = -1; j <= 1; j++){
                            //will add rows, to the i(mover between left in right) + getRows, then will make sure its in bounds by doing the mod
                            int rowCheck = (rows + i + getRows()) % getRows();
                            //0 - 1 + 10 % 10 == 0
                            //0 + 0 + 10 & 10 == 0
                            //0 + 1 + 10 % 10 == 1
                            //9 - 1 +10 == 18 % 10 == 8
                            //9-0 + 10 == 19 & 10 == 9
                            //9 +1 +10 == 20 & 10 == 0
                            //can make an if statement to prevent it from going to 1 once the row reaches getRows()-1 so that it doesnt do anything to the 0 row
                            if(i == 1 && rows == getRows()-1){
                                break;
                            }
                            int colCheck = (cols + j + getCols()) % getCols();

                            if(!(getMines(rowCheck,colCheck))){
                                //if(rows == getRows()-1 && j == 1){
                                //    j++;
                                //}
                                setCalcNumbers(rowCheck,colCheck, mineCounter++);
                            }
                            //setCalcNumbers(rowCheck,colCheck, mineCounter++);
                        }
                        mineCounter = 0;
                    }
                 }

            }
        }


        for (int rows = 0; rows < getRows(); rows++){
            for(int cols = 0; cols < getCols(); cols++){

                //I want to module the rows and cols in order to make sure that it always remains in bounds
                System.out.print(getCalcNumbers(rows % getRows(), cols % getCols()));
                System.out.print(" ");

            }
            System.out.println();
        }



    }

    // Method to display the current state of the board
    public void displayBoard() {
        // TODO: Implement this method
        //sets the whole board to false because nothing is going to be revealed at first
        boolean showboard[][] = new boolean[getRows()][getCols()];
        for(int i = 0; i < getRows();i++){
            for(int j = 0; j < getCols(); j++){
                showboard[i][j] = false;
                setRevealed(showboard);
            }

        }

    }

    // Method to handle a player's move (reveal a cell or place a flag)
    //action flag, would call for the flag to be places
    //action flag, would call for the flag to be taken off if a flag is already there
    public void playerMove(int row, int col, String action) {
        // TODO: Implement this method



    }

    // Method to check if the player has won the game
    //should run after every move
    public boolean checkWin() {
        // TODO: Implement this method

        return false;
    }

    // Method to check if the player has lost the game
    //should run after every move
    public boolean checkLoss(int row, int col) {
        // TODO: Implement this method
        return false;
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    //only reveals adjacent cells if they are 0/not touching a bomb
    private void revealCell(int row, int col) {
        // TODO: Implement this method
    }

    // Method to flag a cell as containing a mine
    //make the cell equal to f, in order to reference that it is a flag
    private void flagCell(int row, int col) {
        // TODO: Implement this method
    }

    // Method to unflag a cell
    //turns the cell from f back to *, meaning back to an unrevealed cell with nothing
    private void unflagCell(int row, int col) {
        // TODO: Implement this method
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char getBoard(int row, int col) {
        return board[row][col];
    }


    public int getNumMines() {
        return numMines;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setMines(boolean[][] mines) {
        this.mines = mines;
    }
    //sets the bomb in the exact spot that you want
    public void setMines(int rows, int cols, boolean result) {
        mines[rows][cols] = result;
    }

    public boolean getMines(int rows, int cols) {
        return mines[rows][cols];
    }

    //makes you set the number that is a bomb to 10, so that it can be different
    public void setCalcNumbers(int row, int col, int number) {
        calcNumbers[row][col] = number;
    }

    public int getCalcNumbers(int row, int col) {
        return calcNumbers[row][col];
    }

    public void setRevealed(boolean[][] revealed) {
        this.revealed = revealed;
    }
}
