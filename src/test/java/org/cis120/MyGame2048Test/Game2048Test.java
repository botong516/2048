package org.cis120.MyGame2048Test;

import org.cis120.MyGame2048.Game2048;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Game2048.
 */
public class Game2048Test {
    @Test
    public void testConstructor() {
        Game2048 game = new Game2048();
        int numOfNonempty = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game.getCell(j, i) != 0) {
                    numOfNonempty += 1;
                    assertEquals(2, game.getCell(j, i));
                }
            }
        }
        assertEquals(2, numOfNonempty);
    }

    @Test
    public void testReset() {
        Game2048 game = new Game2048();
        game.reset();
        int numOfNonempty = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game.getCell(j, i) != 0) {
                    numOfNonempty += 1;
                    assertEquals(2, game.getCell(j, i));
                }
            }
        }
        assertEquals(2, numOfNonempty);
    }

    @Test
    public void testCheckGameOverLose() {
        Game2048 game = new Game2048();
        try {
            game.reloadGame("testCheckGameOverLose.txt");
            assertTrue(game.checkGameOver());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testCheckGameOverNotOver() {
        Game2048 game = new Game2048();
        assertFalse(game.checkGameOver());
    }

    @Test
    public void testGetCell() {
        Game2048 game = new Game2048();
        try {
            game.reloadGame("testCheckGameOverLose.txt");
            assertEquals(8, game.getCell(1, 1));
            assertEquals(4, game.getCell(2, 3));
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testGetScoreDefault() {
        Game2048 game = new Game2048();
        assertEquals(0, game.getScore());
    }

    @Test
    public void testGetScoreLoseDontUpdate() {
        Game2048 game = new Game2048();
        try {
            game.reloadGame("testCheckGameOverLose.txt");
            int s1 = game.getScore();
            game.makeMove(1);
            int s2 = game.getScore();
            assertEquals(s1, s2);
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testGetScoreNoCancellationDontUpdate() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testUnsuccessiveMoveDontGenerateNew.txt");
            assertEquals(0, game.getScore());
            assertEquals(2, game.getCell(0, 0));
            assertEquals(0, game.getCell(0, 1));
            assertEquals(0, game.getCell(0, 2));
            assertEquals(0, game.getCell(0, 3));
            assertEquals(0, game.getCell(1, 0));
            assertEquals(0, game.getCell(1, 1));
            assertEquals(0, game.getCell(1, 2));
            assertEquals(0, game.getCell(1, 3));
            assertEquals(0, game.getCell(2, 0));
            assertEquals(0, game.getCell(2, 1));
            assertEquals(0, game.getCell(2, 2));
            assertEquals(0, game.getCell(2, 3));
            assertEquals(0, game.getCell(3, 0));
            assertEquals(0, game.getCell(3, 1));
            assertEquals(0, game.getCell(3, 2));
            assertEquals(0, game.getCell(3, 3));
            assertEquals(0, game.getScore());
            game.makeMove(2);
            assertEquals(2, game.getCell(3, 0));
            assertEquals(0, game.getScore());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testGetScoreUpdate() {
        Game2048 game = new Game2048();
        try {
            game.reloadGame("testGetScoreUpdate.txt");
            int s1 = game.getScore();
            assertEquals(0, s1);
            game.makeMove(1);
            int s2 = game.getScore();
            assertEquals(4, s2);
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveAllMoveUpNoCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveNoCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(4, game.getCell(2, 1));
            assertEquals(8, game.getCell(1, 2));
            assertEquals(16, game.getCell(2, 2));
            game.makeMove(3);
            assertEquals(2, game.getCell(1, 0));
            assertEquals(4, game.getCell(2, 0));
            assertEquals(8, game.getCell(1, 1));
            assertEquals(16, game.getCell(2, 1));
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveAllMoveDownNoCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveNoCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(4, game.getCell(2, 1));
            assertEquals(8, game.getCell(1, 2));
            assertEquals(16, game.getCell(2, 2));
            game.makeMove(4);
            assertEquals(2, game.getCell(1, 2));
            assertEquals(4, game.getCell(2, 2));
            assertEquals(8, game.getCell(1, 3));
            assertEquals(16, game.getCell(2, 3));
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveAllMoveLeftNoCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveNoCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(4, game.getCell(2, 1));
            assertEquals(8, game.getCell(1, 2));
            assertEquals(16, game.getCell(2, 2));
            game.makeMove(1);
            assertEquals(2, game.getCell(0, 1));
            assertEquals(4, game.getCell(1, 1));
            assertEquals(8, game.getCell(0, 2));
            assertEquals(16, game.getCell(1, 2));
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveAllMoveRightNoCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveNoCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(4, game.getCell(2, 1));
            assertEquals(8, game.getCell(1, 2));
            assertEquals(16, game.getCell(2, 2));
            game.makeMove(2);
            assertEquals(2, game.getCell(2, 1));
            assertEquals(4, game.getCell(3, 1));
            assertEquals(8, game.getCell(2, 2));
            assertEquals(16, game.getCell(3, 2));
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveDownWithCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveWithCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(2, game.getCell(0, 2));
            assertEquals(2, game.getCell(1, 2));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(2, game.getCell(1, 3));
            assertEquals(0, game.getScore());
            game.makeMove(4);
            assertEquals(2, game.getCell(0, 3));
            assertEquals(4, game.getCell(1, 3));
            assertEquals(2, game.getCell(1, 2));
            assertEquals(2, game.getCell(2, 3));
            assertEquals(4, game.getScore());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveLeftWithCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveWithCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(2, game.getCell(0, 2));
            assertEquals(2, game.getCell(1, 2));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(2, game.getCell(1, 3));
            assertEquals(0, game.getScore());
            game.makeMove(1);
            assertEquals(2, game.getCell(0, 1));
            assertEquals(4, game.getCell(0, 2));
            assertEquals(2, game.getCell(0, 3));
            assertEquals(2, game.getCell(1, 2));
            assertEquals(4, game.getScore());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveRightWithCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveWithCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(2, game.getCell(0, 2));
            assertEquals(2, game.getCell(1, 2));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(2, game.getCell(1, 3));
            assertEquals(0, game.getScore());
            game.makeMove(2);
            assertEquals(2, game.getCell(3, 1));
            assertEquals(4, game.getCell(3, 2));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(2, game.getCell(3, 3));
            assertEquals(4, game.getScore());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testMakeMoveUpWithCancelOut() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testMakeMoveWithCancelOut.txt");
            assertEquals(2, game.getCell(1, 1));
            assertEquals(2, game.getCell(0, 2));
            assertEquals(2, game.getCell(1, 2));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(2, game.getCell(1, 3));
            assertEquals(0, game.getScore());
            game.makeMove(3);
            assertEquals(2, game.getCell(0, 0));
            assertEquals(4, game.getCell(1, 0));
            assertEquals(2, game.getCell(2, 0));
            assertEquals(2, game.getCell(1, 1));
            assertEquals(4, game.getScore());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testUnsuccessiveMoveDontGenerateNew() {
        Game2048 game = new Game2048();
        try {
            game.reset();
            game.reloadGame("testUnsuccessiveMoveDontGenerateNew.txt");
            assertEquals(2, game.getCell(0, 0));
            assertEquals(0, game.getCell(0, 1));
            assertEquals(0, game.getCell(0, 2));
            assertEquals(0, game.getCell(0, 3));
            assertEquals(0, game.getCell(1, 0));
            assertEquals(0, game.getCell(1, 1));
            assertEquals(0, game.getCell(1, 2));
            assertEquals(0, game.getCell(1, 3));
            assertEquals(0, game.getCell(2, 0));
            assertEquals(0, game.getCell(2, 1));
            assertEquals(0, game.getCell(2, 2));
            assertEquals(0, game.getCell(2, 3));
            assertEquals(0, game.getCell(3, 0));
            assertEquals(0, game.getCell(3, 1));
            assertEquals(0, game.getCell(3, 2));
            assertEquals(0, game.getCell(3, 3));
            assertEquals(0, game.getScore());
            game.makeMove(1);
            game.makeMove(3);
            assertEquals(2, game.getCell(0, 0));
            assertEquals(0, game.getCell(0, 1));
            assertEquals(0, game.getCell(0, 2));
            assertEquals(0, game.getCell(0, 3));
            assertEquals(0, game.getCell(1, 0));
            assertEquals(0, game.getCell(1, 1));
            assertEquals(0, game.getCell(1, 2));
            assertEquals(0, game.getCell(1, 3));
            assertEquals(0, game.getCell(2, 0));
            assertEquals(0, game.getCell(2, 1));
            assertEquals(0, game.getCell(2, 2));
            assertEquals(0, game.getCell(2, 3));
            assertEquals(0, game.getCell(3, 0));
            assertEquals(0, game.getCell(3, 1));
            assertEquals(0, game.getCell(3, 2));
            assertEquals(0, game.getCell(3, 3));
            assertEquals(0, game.getScore());
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testSuccessiveMoveGenerateNew() {
        Game2048 game = new Game2048();
        try {
            game.reloadGame("testSuccessiveMoveGenerateNew.txt");
            assertEquals(2, game.getCell(0, 0));
            assertEquals(2, game.getCell(0, 1));
            assertEquals(2, game.getCell(0, 2));
            assertEquals(2, game.getCell(0, 3));
            assertEquals(4, game.getCell(1, 0));
            assertEquals(4, game.getCell(1, 1));
            assertEquals(4, game.getCell(1, 2));
            assertEquals(4, game.getCell(1, 3));
            assertEquals(2, game.getCell(2, 0));
            assertEquals(2, game.getCell(2, 1));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(2, game.getCell(2, 3));
            assertEquals(4, game.getCell(3, 0));
            assertEquals(4, game.getCell(3, 1));
            assertEquals(4, game.getCell(3, 2));
            assertEquals(0, game.getCell(3, 3));
            assertEquals(0, game.getScore());
            game.makeMove(2);
            assertEquals(2, game.getCell(0, 0));
            assertEquals(2, game.getCell(0, 1));
            assertEquals(2, game.getCell(0, 2));
            assertEquals(4, game.getCell(1, 0));
            assertEquals(4, game.getCell(1, 1));
            assertEquals(4, game.getCell(1, 2));
            assertEquals(2, game.getCell(1, 3));
            assertEquals(2, game.getCell(2, 0));
            assertEquals(2, game.getCell(2, 1));
            assertEquals(2, game.getCell(2, 2));
            assertEquals(4, game.getCell(2, 3));
            assertEquals(4, game.getCell(3, 0));
            assertEquals(4, game.getCell(3, 1));
            assertEquals(4, game.getCell(3, 2));
            assertEquals(2, game.getCell(3, 3));
            assertEquals(0, game.getScore());

            assertTrue(
                    game.getCell(0, 3) == 2
                            || game.getCell(0, 3) == 4
            );
        } catch (IOException e) {
            System.out.println("The testing file is not found");
        }
    }

    @Test
    public void testSaveGameNull() {
        Game2048 game = new Game2048();
        assertThrows(IOException.class, () -> {
            game.saveGame(null);
        });
    }

    @Test
    public void testSaveGame() {
        Game2048 game1 = new Game2048();
        Game2048 game2 = new Game2048();
        try {
            game1.saveGame("testSaveGame.txt");
            game2.reloadGame("testSaveGame.txt");
            assertEquals(game1.getScore(), game2.getScore());
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game1.getCell(col, row), game2.getCell(col, row));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReloadGameNull() {
        Game2048 game = new Game2048();
        assertThrows(IOException.class, () -> {
            game.reloadGame(null);
        });
    }

    @Test
    public void testReloadGameFileDeleted() {
        Game2048 game = new Game2048();
        try {
            game.saveGame("testReloadGame.txt");
            File hist = new File("testReloadGame.txt");
            hist.delete();
            assertThrows(IOException.class, () -> {
                game.reloadGame("testReloadGame.txt");
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReloadGameFileIsBrokenAddEdition() {
        Game2048 game = new Game2048();
        try {
            game.saveGame("testReloadGame.txt");
            File hist = new File("testReloadGame.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(hist, true));
            bw.write("0");
            bw.close();
            assertThrows(IOException.class, () -> {
                game.reloadGame("testReloadGame.txt");
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReloadGameFileIsBrokenInformationDeleted() {
        Game2048 game = new Game2048();
        game.makeMove(1);
        game.makeMove(2);
        try {
            game.saveGame("testReloadGame.txt");
            File hist = new File("testReloadGame.txt");
            File histCopy = new File("testReloadGameCopy.txt");
            BufferedReader br = new BufferedReader(new FileReader(hist));
            BufferedWriter bw = new BufferedWriter(new FileWriter(histCopy, false));

            String rows = br.readLine();
            br.readLine();
            bw.write(rows + "\n");
            for (int i = 1; i < Integer.parseInt(rows); i++) {
                bw.write(br.readLine() + "\n");
            }
            bw.close();

            assertThrows(IOException.class, () -> {
                game.reloadGame("testReloadGameCopy.txt");
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReloadGameSingleMovesSuccess() {
        Game2048 game1 = new Game2048();
        Game2048 game2 = new Game2048();
        try {
            game1.saveGame("testReloadGame.txt");
            game2.reloadGame("testReloadGame.txt");
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game1.getCell(col, row), game2.getCell(col, row));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReloadGameMultipleMovesSuccess() {
        Game2048 game1 = new Game2048();
        Game2048 game2 = new Game2048();
        game1.makeMove(1);
        game1.makeMove(2);
        game1.makeMove(3);
        game1.makeMove(4);
        try {
            game1.saveGame("testReloadGameMultipleTimes.txt");
            game2.reloadGame("testReloadGameMultipleTimes.txt");
            BufferedReader br = new BufferedReader(
                    new FileReader("testReloadGameMultipleTimes.txt")
            );
            int turn = Integer.parseInt(br.readLine());
            for (int i = turn; i > 0; i--) {
                assertEquals(game1.getScore(), game2.getScore());
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        assertEquals(game1.getCell(col, row), game2.getCell(col, row));
                    }
                }
                if (i != 1) {
                    game1.undo();
                    game2.undo();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUndoNoPreviousMove() {
        Game2048 game1 = new Game2048();
        Game2048 game2 = new Game2048();
        try {
            game1.saveGame("testUndo.txt");
            assertThrows(NoSuchElementException.class, () -> {
                game1.undo();
            });
            game2.reloadGame("testUndo.txt");
            assertEquals(game2.getScore(), game1.getScore());
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game2.getCell(col, row), game1.getCell(col, row));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUndoWithNewMove() {
        Game2048 game1 = new Game2048();
        Game2048 game2 = new Game2048();
        try {
            game1.saveGame("testUndo.txt");
            assertThrows(NoSuchElementException.class, () -> {
                game1.undo();
            });
            game2.reloadGame("testUndo.txt");
            game1.makeMove(1);

            game1.undo();
            assertEquals(game2.getScore(), game1.getScore());
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game2.getCell(col, row), game1.getCell(col, row));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUndoMultipleUndo() {
        Game2048 game1 = new Game2048();
        Game2048 game2 = new Game2048();
        // Ensure there is enough successful movement
        game1.makeMove(1);
        game1.makeMove(2);
        game1.makeMove(3);
        game1.makeMove(4);
        game1.makeMove(3);
        game1.makeMove(4);
        game1.makeMove(1);
        game1.makeMove(2);
        try {
            game1.saveGame("testUndo.txt");
            game2.reloadGame("testUndo.txt");

            assertEquals(game1.getScore(), game2.getScore());
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game2.getCell(col, row), game1.getCell(col, row));
                }
            }
            game1.undo();
            game2.undo();

            assertEquals(game1.getScore(), game2.getScore());
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game2.getCell(col, row), game1.getCell(col, row));
                }
            }
            game1.undo();
            game2.undo();

            assertEquals(game1.getScore(), game2.getScore());
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    assertEquals(game2.getCell(col, row), game1.getCell(col, row));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
