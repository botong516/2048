# 2048
<img src="https://github.com/botong516/Personal-Java-Projects/blob/main/2048/2048.png" width="400" height="280" />   
In this project, I created a playable 2048 game using the Java Swing GUI framework. The game fulfills the regular 2048 game rule and supports game 
saving and reloading.   

### How to Play   
1. To run your game, right-click on ``Game.java`` and select ``Run Game.main()``. Now you are ready to play!
2. Use arrow keys to move the tiles;   
3. Click corresponding buttons to undo, save game, reload game, start new game, etc.   

### Classes Overview
- **Run2048**   
  ``Run2048`` class includes all the GUI components I used for this game. In particular,
  it contains two ``JFrame`` and several ``JComponents`` in each of the frame.   
  
  The first window that will pop up when running this game is an instruction window. I create a class called ``GameInstruction``,
  which extends ``JTextArea``, to store the document of the game instruction. Under the text area, I
  create a panel including two buttons that allow the user to choose whether to start a new game
  or continue an old game. The Continue buttion will be greyed out if there is no previously
  saved game. User can always click for a new game. Both of the two buttons include action
  listener. When the New Game/Continue button is clicked, the main game window is packed and setted
  to be visible and a new/old game will be setted up. At the same time, the instuction window will
  disappear.   
  
  When comming into the real game window, the frame contains a Gameboard component, which extends
  the ``JPanel`` class; a control panel, which includes the "Restart", "Save Game", and "Undo" buttons for
  game functionality; and a status panel, which display the current status and any notification
  message as the user is playing the game. The status panel also record the current score.
  Each button is added with an action listen and call the corresponding methods when an action is
  made.   
  
- **GameBoard2048**   
  My ``GameBoard2048`` class extends ``JPanel``, and it basically displays the game board and control the
  game status text by setting texts according to the two labels in the parameter.   
  
  The GameBoard first initialize a ``Game2048`` object as a game model. And the GameBoard contains a key listener to
  capture the press of the four arrow keys on keyboard and determine which direction the user
  wants to move.   
  
  In response to the click of "New Game" or "Continue" in the instruction window, the
  corresponding reset and reload methods are called in the ``GameBoard2048`` class and the appropriate
  status text is setted to tell the user whether the game is reloaded correctly or a new game is
  started if failed to reload the old game. In response to the buttons on game window, the ``GameBoard2048`` 
  also just call the corresponding method of the model, and set the corresponding text.   
  
  By encapsulation, the ``GameBoard2048`` does not implement any function that
  operate the game but only control what is being displayed and call the right methods in ``Game2048``
  class. When any buttons are clicked and arrow keys are pressed, the ``GameBoard2048`` updates the
  status of the text and repaint the board.   
  
- **Game2048**   
  The ``Game2048`` class is where the essential game playing function is stored. It stores a collection
  of all the board state after each move and a list of corresponding scores. The class contains the most
  important move feature in which every tile is moved toward the specified direction and
  cancellation is successfully made if necessary.   
  
  The class provide a way to check if the game is over, meaning there is no more move can be made according 
  the 2048 game rule. The class also contains save and reload methods. When saving the game, the board states and scores
  are all written to the ``game_state.txt`` file. For reloading game, the txt file is read and
  the last board state, last score, all board states with corresponding score will be extracted and
  stored as the current game state. The reload game method also deal with the situation when the
  game history file is deleted, the content is edited, and these scenarios will be considered as the
  history is lost and ``IOException`` will be thrown to tell this issue so that ``GameBoard2048`` class can
  catch the Exception and display error message. The ``Game2048`` class also contains getter methods,
  which allows other class to get the current score and entries in the current board.
