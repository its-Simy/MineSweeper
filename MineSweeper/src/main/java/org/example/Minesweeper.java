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
    private int turnNum;
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
        this.turnNum = 0;

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
    private void initializeBoard() {
        // TODO: Implement this method
        int r = getRows();
        int c = getCols();
        char gameboard[][] = new char[r][c];
        for(int i = 0; i < r;i++){
            for(int j = 0; j < c; j++){
                gameboard[i][j] = '*';
                setBoard(gameboard);
            }
        }
        for(int i = 0; i < getRows();i++){
            for(int j = 0; j < getCols(); j++){
                revealed[i][j] = false;
            }
        }



    }

    // Method to randomly place mines on the board
    private void placeMines() {
        // TODO: Implement this method
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
    }


    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        // TODO: Implement this method

        for (int rows = 0; rows < getRows(); rows++){
            for(int cols = 0; cols < getCols(); cols++){
                    if(getCalcNumbers(rows,cols) == 9){
                    for(int i =-1; i <=1 ; i++){
                        for(int j = -1; j <= 1; j++) {
                            if (i == 0 && j == 0)
                                continue;
                            int rowCheck = rows + i;
                            int colCheck = cols + j;
                            if (rowCheck >= 0 && rowCheck < getRows() && colCheck >= 0 && colCheck < getCols()) {
                                if (!(getMines(rowCheck, colCheck)))
                                    setCalcNumbers(rowCheck, colCheck, getCalcNumbers(rowCheck, colCheck) + 1);
                            }
                        }

                    }
                 }
            }
        }
    }

    // Method to display the current state of the board
    public void displayBoard() {
        // TODO: Implement this method
        if(turnNum == 0){
            initializeBoard();
            placeMines();
            calculateNumbers();
        }
        turnNum++;

        for(int i = 0; i < getRows();i++){
            for(int j = 0; j < getCols(); j++){
                System.out.print(getBoard(i,j) + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    //action flag, would call for the flag to be places
    //action flag, would call for the flag to be taken off if a flag is already there
    public void playerMove(int row, int col, String action) {
        // TODO: Implement this method
        if(!gameOver) {
            if(getBoard(row,col) == 'f' && action.equals("reveal"))
            {
                System.out.println("Player must unflag before revealing");
                System.out.println();

            }
            else if (!getRevealed(row, col) && action.equals("flag") && getBoard(row,col) != 'f') {
                flagCell(row, col);
                System.out.println();
            }
            else if ((!getRevealed(row, col) && action.equals("unflag")) && getBoard(row,col) == 'f') {
                unflagCell(row, col);
                System.out.println();
            }
            else {
                if (getRevealed(row, col) && action.equals("reveal")){
                    System.out.println("Choose another cell, This cell is already revealed");
                    System.out.println();
                         }
                else {
                    if(!getRevealed(row,col) && action.equals("reveal"))
                        revealCell(row, col);
                }
            }
        }
        else
            System.out.println("The game is over, start over to play again");
    }
    // Method to check if the player has won the game
    //should run after every move
    public boolean checkWin() {
        // makesure to reveal the cell first then, check for the win
        // TODO: Implement this method
        int totalNonBombs = (getRows() * getCols()) - numMines;
        int tracker = 0;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if(getRevealed(i,j)){
                    tracker++;
                    if(totalNonBombs == tracker) {
                        setGameOver(true);
                        System.out.println("You beat MineSweeper!");
                        return true;
                    }
                }

            }
        }

            return false;
    }

    // Method to check if the player has lost the game
    //should run after every move
    public boolean checkLoss(int row, int col) {
        // TODO: Implement this method

        if(getMines(row,col)){
            return true;
        }

        return false;
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    //only reveals adjacent cells if they are 0/not touching a bomb
    private void revealCell(int row, int col) {
        // TODO: Implement this method
        setRevealed(row,col);
        int cellreveal = getCalcNumbers(row, col);//int im trying to convert into char
        char newcell = (char)(cellreveal +  '0');//casts the int into the character
        setBoard(row, col, newcell);//updates the character on the reveal board

    }

    // Method to flag a cell as containing a mine
    //make the cell equal to f, in order to reference that it is a flag
    private void flagCell(int row, int col) {
        // TODO: Implement this method

        setBoard(row,col, 'f');

    }

    // Method to unflag a cell
    //turns the cell from f back to *, meaning back to an unrevealed cell with nothing
    private void unflagCell(int row, int col) {
        // TODO: Implement this method

        setBoard(row,col,'*');

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

    public void setBoard(int rows, int cols, char thing) {
        board[rows][cols] = thing;
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
    public void setRevealed(int row, int col) {
        this.revealed[row][col] = true;
    }

    public boolean getRevealed(int row, int col) {
        return revealed[row][col];
    }



}
