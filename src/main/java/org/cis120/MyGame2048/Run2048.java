package org.cis120.MyGame2048;

/**
 * CIS 120 HW09 - 2048 Demo
 * (c) University of Pennsylvania
 * Created by Botong Zhang in Fall 2021.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. In particular,
 * it contains two JFrame and several JComponents in each of the frame. The
 * first window that will
 * pop up with running this game is an instruction window. I create a class
 * called GameInstruction,
 * which extends JTextArea, to store the document of the game instruction. Under
 * the text area, I
 * create a panel including two buttons that allow the user to choose whether to
 * start a new game
 * or continue an old game. The Continue button is will be greyed out if this
 * is no previously
 * saved game. And User can always click for a new game. Both of the two buttons
 * include action
 * listener. When the New Game/Continue button is clicked, the main game window
 * is packed and setted
 * to be visible and a new/old will be setted up. At the same time, the
 * instruction window will
 * disappear.
 *
 * When coming into the real game window, the frame contains a Gameboard
 * component, which extends
 * the JPanel class, a control panel, which includes the Restart, Save Game, and
 * Undo buttons for
 * game functionality, and a status panel, which display the current status and
 * any notification
 * message as the user is playing the game, and the status panel also record the
 * current score.
 * Each button is added with an action listen and call the corresponding methods
 * when an action is
 * made.
 */
public class Run2048 implements Runnable {
    public void run() {
        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(400, 400);

        // Status score panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);
        final JLabel score = new JLabel("Score: 0");
        score.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        status_panel.add(score);

        // Game board
        final GameBoard2048 board = new GameBoard2048(status, score);
        frame.add(board, BorderLayout.CENTER);

        // Reset, Save Game, and Undo buttons
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Restart");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        control_panel.add(reset);

        final JButton saveGame = new JButton("Save Game");
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.saveGame("game_state.txt");
            }
        });
        control_panel.add(saveGame);

        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.undo();
            }
        });
        control_panel.add(undo);

        // Pre-level frame in which game instructions is shown and user can choose
        // to start a new game or reload the last game
        final JFrame preFrame = new JFrame("2048");
        preFrame.setLocation(400, 400);

        // Instruction panel
        final JPanel instruction_panel = new JPanel();
        preFrame.add(instruction_panel, BorderLayout.CENTER);
        final GameInstruction text = new GameInstruction();
        instruction_panel.add(text);

        // New Game and Continue Buttons
        final JPanel enter_panel = new JPanel();
        preFrame.add(enter_panel, BorderLayout.SOUTH);

        final JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Put the frame on the screen
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                // Start the game
                board.reset();

                preFrame.setVisible(false);
            }
        });
        enter_panel.add(newGame);

        final JButton oldGame = new JButton("Continue");
        oldGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (new File("game_state.txt").exists()) {
                    // Put the frame on the screen
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                    // Start the game
                    board.reload("game_state.txt");

                    // Invisible the instructions window
                    preFrame.setVisible(false);
                }
            }
        });
        if (!(new File("game_state.txt").exists())) {
            oldGame.setForeground(Color.GRAY);
        }
        enter_panel.add(oldGame);

        // Put the instruction frame on screen
        preFrame.pack();
        preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        preFrame.setVisible(true);
    }
}