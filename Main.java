package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

// Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);


        // Game loop
        while (!game.getGameOver()) {
            //game.displayBoard();
            // Get player input for row, col, and action (reveal or flag)
            // For now, just simulate a move (to be replaced with real player input logic

            // Check for win or loss conditions
            //if the check win returns true, the game ends and returns congrats
            if (game.checkWin()) {

                System.out.println("Congratulations! You've won the game.");
                break;
            }
            //if the checkLoss returns true, meaning you lost, you get game over return
            if (game.checkLoss(0, 0)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
        game.playerMove(5, 3, "reveal");




    }
}