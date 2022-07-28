package org.cis120.MyGame2048;

/**
 * CIS 120 HW09 - 2048 Demo
 * (c) University of Pennsylvania
 * Created by Botong Zhang in Fall 2021.
 */

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * This class is a model for 2048.
 * 
 * This model includes the game board and record the score of every step during
 * the game play.
 * It also has method to "play" the game by moving the tiles into a particular
 * direction, and
 * the method to "undo" the last step, and it also supports the function of
 * saving and reloading
 * the previously saved game stored in the game state file. The reset method
 * indicates to start or
 * restart a new game.
 *
 */
public class Game2048 {

    private LinkedList<int[][]> moves;
    private LinkedList<Integer> scores;
    private int score;
    private int[][] board;

    /**
     * Constructor sets up game state.
     */
    public Game2048() {
        reset();
    }

    /**
     * checkGameOver checks whether the game has reached a game over condition,
     * namely check if there is any move can still be made.
     *
     */
    public boolean checkGameOver() {
        boolean ifContinue = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j + 1 < 4) {
                    ifContinue = ifContinue || (board[i][j] == board[i][j + 1])
                            || (board[i][j] == 0);
                }
                if (i + 1 < 4) {
                    ifContinue = ifContinue || (board[i][j] == board[i + 1][j])
                            || (board[i][j] == 0);
                }
            }
        }
        return !ifContinue;
    }

    /**
     * printGameState prints the current game state for debugging.
     */
    public void printGameState() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    /**
     * reset (re-)sets the game state to start a new game, in which two 2's are
     * randomly generated on the board.
     */
    public void reset() {
        board = new int[4][4];
        score = 0;
        boolean gameOver = false;
        Random rand = new Random();

        // Generate two 2's in random positions in the board
        int r1 = rand.nextInt(4);
        int c1 = rand.nextInt(4);
        int r2 = rand.nextInt(4);
        int c2;

        do {
            c2 = rand.nextInt(4);
        } while (r1 == r2 && c1 == c2);

        board[r1][c1] = 2;
        board[r2][c2] = 2;

        moves = new LinkedList<>();
        moves.add(board);

        scores = new LinkedList<>();
        scores.add(score);
    }

    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the number stored in the cell
     */
    public int getCell(int c, int r) {
        return board[r][c];
    }

    /**
     * getScore is a getter for the current score of the game.
     *
     * @return an integer denoting the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * The method makes move according to the input direction. It also cancels out
     * the first
     * adjacent pairs along that direction that have same numbers by combining them
     * into a new
     * tile displaying their sum. When moving up or down, every column could only
     * cancel once.
     * When moving to left or right, every row could only cancel once. After every
     * successive
     * move, namely at least one tile changes position or cancellation has been
     * made. A new 2 or
     * 4 will be generated in a randomly selected empty tile.
     *
     * @param direction the direction to move. 1 = move to left; 2 = move to right;
     *                  3 = move up; 4 = move down.
     */
    public void makeMove(int direction) {
        cleanSpace(direction);
        switch (direction) {
            case 1:
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        if (board[row][col] != 0
                                && col - 1 >= 0 && (board[row][col] == board[row][col - 1])) {
                            score += (2 * board[row][col]);
                            board[row][col - 1] = 2 * board[row][col];
                            board[row][col] = 0;
                            break;
                        }
                    }
                }
                break;

            case 2:
                for (int row = 0; row < 4; row++) {
                    for (int col = 3; col >= 0; col--) {
                        if (board[row][col] != 0
                                && col + 1 < 4 && (board[row][col] == board[row][col + 1])) {
                            score += (2 * board[row][col]);
                            board[row][col + 1] = 2 * board[row][col];
                            board[row][col] = 0;
                            break;
                        }
                    }
                }
                break;

            case 3:
                for (int col = 0; col < 4; col++) {
                    for (int row = 0; row < 4; row++) {
                        if (board[row][col] != 0
                                && row - 1 >= 0 && (board[row][col] == board[row - 1][col])) {
                            score += (2 * board[row][col]);
                            board[row - 1][col] = 2 * board[row][col];
                            board[row][col] = 0;
                            break;
                        }
                    }
                }
                break;

            case 4:
                for (int col = 0; col < 4; col++) {
                    for (int row = 3; row >= 0; row--) {
                        if (board[row][col] != 0
                                && row + 1 < 4 && (board[row][col] == board[row + 1][col])) {
                            score += (2 * board[row][col]);
                            board[row + 1][col] = 2 * board[row][col];
                            board[row][col] = 0;
                            break;
                        }
                    }
                }
                break;

            default:
                break;
        }
        cleanSpace(direction);
        // Generate a new 2 in a randomly chosen empty grid if tiles are successively
        // moved
        if (!noMove(board, moves.getLast())) {
            generateOne();
            // Store the current board state into the "moves" collection.
            moves.add(board);
            // Store the current score into the "scores" collection
            scores.add(score);
        }
    }

    /**
     * This method move all tiles toward the input direction without doing
     * cancellation.
     * 
     * @param direction the direction to move. 1 means move to left; 2 means move to
     *                  right;
     *                  3 means move up; 4 means move down.
     */
    private void cleanSpace(int direction) {
        int[][] cleaned = new int[4][4];
        switch (direction) {
            case 1:
                for (int oldR = 0; oldR < 4; oldR++) {
                    for (int oldC = 0; oldC < 4; oldC++) {
                        if (board[oldR][oldC] != 0) {
                            for (int newC = 0; newC < 4; newC++) {
                                if (cleaned[oldR][newC] == 0) {
                                    cleaned[oldR][newC] = board[oldR][oldC];
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            case 2:
                for (int oldR = 0; oldR < 4; oldR++) {
                    for (int oldC = 3; oldC >= 0; oldC--) {
                        if (board[oldR][oldC] != 0) {
                            for (int newC = 3; newC >= 0; newC--) {
                                if (cleaned[oldR][newC] == 0) {
                                    cleaned[oldR][newC] = board[oldR][oldC];
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            case 3:
                for (int oldC = 0; oldC < 4; oldC++) {
                    for (int oldR = 0; oldR < 4; oldR++) {
                        if (board[oldR][oldC] != 0) {
                            for (int newR = 0; newR < 4; newR++) {
                                if (cleaned[newR][oldC] == 0) {
                                    cleaned[newR][oldC] = board[oldR][oldC];
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            case 4:
                for (int oldC = 0; oldC < 4; oldC++) {
                    for (int oldR = 3; oldR >= 0; oldR--) {
                        if (board[oldR][oldC] != 0) {
                            for (int newR = 3; newR >= 0; newR--) {
                                if (cleaned[newR][oldC] == 0) {
                                    cleaned[newR][oldC] = board[oldR][oldC];
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            default:
                break;
        }
        board = cleaned;
    }

    /**
     * This method randomly generates a 2 or 4 to a randomly selected empty tile in
     * the board.
     */
    private void generateOne() {
        // Check if there is empty space
        boolean hasEmpty = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                hasEmpty = hasEmpty || board[i][j] == 0;
            }
        }

        if (hasEmpty) {
            Random rand = new Random();
            int num = rand.nextInt();
            int row;
            int col;

            do {
                row = rand.nextInt(4);
                col = rand.nextInt(4);
            } while (board[row][col] != 0);

            if (num % 2 == 0) {
                board[row][col] = 2;
            } else {
                board[row][col] = 4;
            }
        }
    }

    /**
     * Save all moves into a file.
     *
     * @param file the file name that game history is going to be saved to.
     * @throws IOException The method will throw an IOException if there is any IO
     *                     error
     *                     * occurs, for example, if the saving file is accidentally
     *                     deleted.
     */
    public void saveGame(String file) throws IOException {
        // File gameState = Paths.get(file).toFile();
        if (file == null) {
            throw new IOException("Input Cannot be null!");
        }

        File gameState = new File(file);
        BufferedWriter bw;
        int numOfTurns = moves.size();

        if (!gameState.createNewFile()) {
            bw = new BufferedWriter(new FileWriter(gameState, false));
        } else {
            bw = new BufferedWriter(new FileWriter(gameState));
        }
        bw.write(numOfTurns + "\n");
        for (int row = 0; row < moves.size(); row++) {
            bw.write(scores.get(row) + " ");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    bw.write(moves.get(row)[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.close();
    }

    /**
     * This method reload the previous saved game.
     *
     * @param file the name of the file saving the game history
     * @throws IOException this method throw an IOException if the file if empty or
     *                     the game
     *                     history file is edited, meaning that game history is
     *                     broken.
     */
    public void reloadGame(String file) throws IOException {
        if (file == null) {
            throw new IOException("Input cannot be null!");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String turn;
        int numOfTurns;
        moves = new LinkedList<>();
        scores = new LinkedList<>();

        turn = br.readLine();
        // First check if the file is empty
        if (turn == null) {
            throw new IOException("File is Empty!");
        } else {
            numOfTurns = Integer.parseInt(turn.trim());
            turn = br.readLine();
            while (turn != null) {
                // Extract the move
                String[] oneMove = turn.split(" ");
                // Check if line has all data needed, namely the game history stored is correct
                if (oneMove.length != 17) {
                    throw new IOException("Game History is broken!");
                } else {
                    scores.add(Integer.parseInt((oneMove[0]).trim()));
                    int[][] temporary = new int[4][4];
                    int entry = 1;
                    for (int row = 0; row < 4; row++) {
                        for (int col = 0; col < 4; col++) {
                            temporary[row][col] = Integer.parseInt(oneMove[entry]);
                            entry += 1;
                        }
                    }
                    moves.add(temporary);
                }
                turn = br.readLine();
            }
            if (moves.size() != numOfTurns) {
                throw new IOException("Game History is broken!");
            }
            board = moves.getLast();
            score = scores.getLast();
        }
    }

    /**
     * Undo the previous move. Set board state into the one before the last move was
     * done. It will throw a NullSuchElementException is there is no previous step
     * to undo.
     */
    public void undo() {
        if (moves.size() == 1) {
            throw new NoSuchElementException();
        }
        moves.removeLast();
        board = moves.getLast();
        scores.removeLast();
        score = scores.getLast();
    }

    /**
     * Check if there was a successive move, namely, check if the current board
     * state is same as
     * the state before making the move.
     *
     * @param b1 board1
     * @param b2 board2
     * @return boolean return true if board 1 has same state as board 2
     */
    private boolean noMove(int[][] b1, int[][] b2) {
        boolean same = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                same = same && b1[i][j] == b2[i][j];
            }
        }
        return same;
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
    }
}
