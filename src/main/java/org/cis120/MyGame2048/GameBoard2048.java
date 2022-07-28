package org.cis120.MyGame2048;

/**
 * CIS 120 HW09 - 2048
 * (c) University of Pennsylvania
 * Created by Botong Zhang in Fall 2021.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.*;
import java.io.*;
import java.util.NoSuchElementException;

/**
 * This class instantiates a 2048 object, which is the model for the game.
 * As the user clicks the left, right up, or down arrows on keyboard, the
 * model is updated. Whenever the model is updated, the game board repaints
 * itself.
 *
 */
public class GameBoard2048 extends JPanel {

    private Game2048 model2048; // model for the game
    private JLabel status; // current status text
    private JLabel score;

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard2048(JLabel statusInit, JLabel scoreInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        model2048 = new Game2048(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        score = scoreInit;

        /*
         * Listens for keyboard presses. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    model2048.makeMove(1);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    model2048.makeMove(2);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    model2048.makeMove(3);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    model2048.makeMove(4);
                }
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        model2048.reset();
        status.setText("Setting up...");
        score.setText("Score: 0");

        repaint();
        requestFocusInWindow();
    }

    /**
     * Reload the previously stored game state.
     * 
     * @param fileName the name of the file that store the game history
     */
    public void reload(String fileName) {
        try {
            model2048.reloadGame(fileName);
            status.setText("Game reloaded!");
            score.setText("Score: " + model2048.getScore());
        } catch (IOException e) {
            reset();
            status.setText("Game history is lost! A new game is started!");
        } finally {
            repaint();
            requestFocusInWindow();
        }
    }

    /**
     * Save the current game status into a .txt file for user to reload later.
     *
     * @param fileName the name of the file that is going to store the game history.
     */
    public void saveGame(String fileName) {
        try {
            model2048.saveGame(fileName);
            status.setText("Game Saved!");
        } catch (IOException e) {
            status.setText("Failed to save the game!");
        } finally {
            repaint();
            requestFocusInWindow();
        }
    }

    /**
     * Undo the previous move.
     */
    public void undo() {
        try {
            model2048.undo();
            updateStatus();
        } catch (NoSuchElementException e) {
            status.setText("No previous move!");
        } finally {
            repaint();
            requestFocusInWindow();
        }
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (model2048.checkGameOver()) {
            status.setText("Game Over!");
        } else {
            score.setText("Score: " + model2048.getScore());
            status.setText("Game in progress...");
        }
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font stringFont = new Font("SansSerif", Font.BOLD, 20);
        g.setFont(stringFont);

        // Draws board grid
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);
        g.drawLine(0, 0, 400, 0);
        g.drawLine(0, 0, 0, 400);
        g.drawLine(0, 400, 400, 400);
        g.drawLine(400, 0, 400, 400);

        // Draw numbers in each grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int state = model2048.getCell(i, j);
                if (state != 0) {
                    if (state < 10) {
                        g.drawString(String.valueOf(state), 45 + 100 * i, 55 + 100 * j);
                    } else if (state < 100) {
                        g.drawString(String.valueOf(state), 40 + 100 * i, 55 + 100 * j);
                    } else if (state < 1000) {
                        g.drawString(String.valueOf(state), 35 + 100 * i, 55 + 100 * j);
                    } else {
                        g.drawString(String.valueOf(state), 30 + 100 * i, 55 + 100 * j);
                    }
                }
            }
        }

    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
