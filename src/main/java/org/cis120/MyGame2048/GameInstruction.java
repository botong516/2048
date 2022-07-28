package org.cis120.MyGame2048;

/**
 * CIS 120 HW09 - 2048 Demo
 * (c) University of Pennsylvania
 * Created by Botong Zhang in Fall 2021.
 */

import javax.swing.*;

/**
 * This class is a text area which includes the instruction document for the
 * Game 2048.
 */
public class GameInstruction extends JTextArea {
    final String instruction = "===================================================" + "\n" +
            "--------------------------Welcome to 2048-----------------------" + "\n" +
            "===================================================" + "\n" +
            "2048 is a simple one-player game. It has a 4x4 grid and each grid will be " +
            "\n" +
            "either empty or display a that is a power of 2. When you first start the" +
            "\n" +
            "game, only two random grids will show a 2 in each. " +
            "\n" + "\n" +
            "What you should do is press either of the four arrow keys to try to tilt" +
            "\n" +
            "the entire game board into one direction and all the tiles will move toward" +
            "\n" +
            "that direction. For example, if you press the 'UP' arrow on the keyboard," +
            "\n" +
            "all tiles will move up to the top. And during the moving, if any adjacent " +
            "\n" +
            "tiles have the same number, they will be combined into one tile with the " +
            "\n" +
            "number of their sum and your score will be increased by the number displayed" +
            "\n" +
            "in the new tile. " +
            "\n" + "\n" +
            "The rule for canceling out tiles is the following: when you are moving toward " +
            "\n" +
            "UP or DOWN, every column of your board will only cancel once even if they have " +
            "\n" +
            "multiple same adjacent tiles. The pair that will be canceled first is the first" +
            "\n" +
            "adjacent and same-valued pair starting from the direction you are moving to." +
            "\n" +
            "Similarly, for pressing LEFT or RIGHT, every row of your board will only cancel" +
            "\n" +
            "once." +
            "\n" + "\n" +
            "Every time you successfully make a movement, meaning at least one tile was moved" +
            "\n" +
            "after your key pressing, a new 2 or 4 will automatically appear to one of" +
            "\n" +
            "the randomly selected empty tiles. If there is no more empty place to generate a new" +
            "\n" +
            "number, and there is no more movement can be made, namely, there is no adjacent " +
            "\n" +
            "pair that has the same value, the game is over." +
            "\n" + "\n" +
            "You can choose to start a New game now or reload an old game that you've saved. " +
            "\n" +
            "Once you enter into the game window, you cannot go back to this page except quit" +
            "\n" +
            "the game and open it again. Your game state will not be automatically saved! The " +
            "\n" +
            "Continue button will only reload the game you saved. You can always press the New" +
            "\n" +
            "Game button, but the Continue button will be greyed out if there is no game history" +
            "\n" +
            "saved before, and clicking that will give no effect." +
            "\n" + "\n" +
            "After you made your choice of Starting a new game or an old game, you are entering" +
            "\n" +
            "the core game page. In this window, you can choose to start a new game, save your" +
            "\n" +
            "game status, or undo a step. At the bottom of the page, you will see the current" +
            "\n" +
            "status of your game and appropriate messages will also pop up over there to tell" +
            "\n" +
            "you if your command is successfully operated. For example, the message will show" +
            "\n" +
            "you whether your game is successfully saved or if your previous game is successfully" +
            "\n" +
            "reloaded. Or, if there is an undo you can make or not. At the bottom of this window," +
            "\n" +
            "you can also see your current score. And if you choose to continue an old game," +
            "\n" +
            "everything will be reloaded, and you can still undo your movements even those were " +
            "\n" +
            "done in the previous turn." +
            "\n" + "\n" +
            "Now, it is time to start playing!";

    public GameInstruction() {
        super();
        super.append(instruction);
    }
}
