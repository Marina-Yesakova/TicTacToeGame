package tictactoegame;

import java.util.Scanner;

/**
 * @author Maryna Yesakova
 */
public class TicTacToeGame {
    private char[][] board = {{'1', '2', '3', '4'},
            {'5', '6', '7', '8'},
            {'9', 'a', 'b', 'c'},
            {'d', 'e', 'f', 'g'}};
    ; //a private two-dimensional array
    public boolean status;// false still playing, true = end of the game
    public String winner; // holds X won, O won, tie
    public boolean whoseTurn;//true = turn X; false turn O;

    /**
     * No-parameter constructor that creates the initial setting on the board
     */
    TicTacToeGame() {
        this.status = false;
        this.winner = "Start of the game";
        this.whoseTurn = true;
    }

    /**
     * void method that prints the current board
     */
    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }

    /**
     * void method input() provides the prompt and gathers input from the user
     */
    public void input() {
        this.printBoard();
        //tracks whose turn it is to play
        if (whoseTurn == true) {
            System.out.println("X turn it is now to play");
        } else {
            System.out.println("O turn it is now to play");
        }

        Scanner keyboard = new Scanner(System.in);
        String input = null;//imput from player 
        boolean again = true;
        char charInput = 0;// convert player input into char
        char[][] firstBoard = {{'1', '2', '3', '4'},
                {'5', '6', '7', '8'},
                {'9', 'a', 'b', 'c'},
                {'d', 'e', 'f', 'g'}};
        //validation loop
        while (again) {
            System.out.println("Please enter char from '1' to '9' or from 'a' to 'g'");
            input = keyboard.nextLine();
            if (input.length() != 1) {
                System.out.println("Your input should be one character.");
                continue;
            } else {
                charInput = input.charAt(0);
                if (charInput < '0' || charInput > '9' && charInput < 'a' || charInput > 'g') {
                    System.out.println("Your input should be char from '1' to '9' or "
                            + "from 'a' to 'g'.");
                    continue;
                } else {
                    again = false;
                    //System.out.println("Loop finish");
                }
            }

            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    if (firstBoard[i][j] == charInput) {
                        if (board[i][j] == 'x' || board[i][j] == '0') {
                            System.out.println("X or O already standing there. Please try again");
                            break;
                        } else if (board[i][j] == charInput && whoseTurn == true) {
                            board[i][j] = 'x';
                            whoseTurn = false;

                        } else if (board[i][j] == charInput && whoseTurn == false) {
                            board[i][j] = '0';
                            whoseTurn = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * void method analyzeBoard analyzes the current board and determines if there is
     * a winning position present or if it is a tie.
     */
    public void analyzeBoard() {
        //crating char array[3][3]to copy 3x3 board from 4x4 for checking for the winner
        char[][] boardTriple = new char[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                boardTriple[i][j] = board[i][j];
            }
        }

        if (this.hasWon(boardTriple, 'x')) {
            //System.out.println("win x1");
            winner = "X";
            status = true;
            return;
        }

        if (this.hasWon(boardTriple, '0')) {
            //System.out.println("win 0.1");
            winner = "O";
            status = true;
            return;
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                boardTriple[i][j] = board[i][j + 1];
            }
        }

        if (this.hasWon(boardTriple, 'x')) {
            //System.out.println("win x2");
            winner = "X";
            status = true;
            return;
        }

        if (this.hasWon(boardTriple, '0')) {
            //System.out.println("win 0.2");
            winner = "O";
            status = true;
            return;
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                boardTriple[i][j] = board[i + 1][j];
            }
        }

        if (this.hasWon(boardTriple, 'x')) {
            //System.out.println("win x3");
            winner = "X";
            status = true;
            return;
        }

        if (this.hasWon(boardTriple, '0')) {
            //System.out.println("win 0.3");
            winner = "O";
            status = true;
            return;
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                boardTriple[i][j] = board[i + 1][j + 1];
            }
        }

        if (this.hasWon(boardTriple, 'x')) {
            //System.out.println("win x4");
            winner = "X";
            status = true;
            return;
        }

        if (this.hasWon(boardTriple, '0')) {
            //System.out.println("win 0.4");
            winner = "O";
            status = true;
            return;
        }

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (board[i][j] != '0' && board[i][j] != 'x') {
                    return;
                }
            }
        }

        winner = "T";
        status = true;
        System.out.println("This is tie");
    }

    /**
     * method hasWon which takes parameters board and symbol and return boolean
     * true if this symbol won otherwise return false
     *
     * @param board  for checking the current board for winner
     * @param symbol which symbol is checking now for winning 0 or x
     * @return boolean true if symbol won, or false if not won
     */
    public boolean hasWon(char[][] board, char symbol) {
        // split the board 4x4 for 3x3 for cheking the winner
        //create 4 bool variable for this operation: two for diagonal leftToprightBottom, rightTopLeftBottom
        //and horisontal and vertical.
        boolean leftToprightBottom = board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == symbol;
        boolean rightTopLeftBottom = board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == symbol;
        if (leftToprightBottom || rightTopLeftBottom) {
            //System.out.println("Diagonal Win");
            return true;
        }

        for (int i = 0; i < 3; ++i) {
            boolean horisontal = board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == symbol;
            boolean vertical = board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == symbol;
            if (horisontal || vertical) {
                //System.out.println("Row or Column win");
                return true;
            }
        }

        return false;
    }

    /**
     * accessor method for status field. Method returns true if the game came to the end
     *
     * @return status
     */
    public boolean done() {
        return status;
    }

    /**
     * accessor for winner field. Method returns the name of winner
     *
     * @return winner (T, O or X)
     */
    public String whoWon() {
        System.out.println("Winner is: " + winner);
        return winner;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TicTacToeGame game = new TicTacToeGame();
        boolean again = true;//controls the loop
        while (again) {
            game.input();
            game.analyzeBoard();
            if (game.done() == true) {
                again = false;
            }
        }

        game.printBoard();
        game.whoWon();
    }
}
