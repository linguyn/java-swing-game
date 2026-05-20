public class Project2Runner {
    
    /*
     * Name: <Linh Nguyen>
     * Student ID: <501278478>
     * 
     ******** Project Description ********
     * 
     * The "Classroom Bandit" game is a simple 2D game built using Java Swing and 2D Graphics. 
     * The player controls a student who tries to talk to classmates without getting caught by the teacher. 
     * The teacher randomly turns around to check for students misbehaving. If the teacher catches the player talking, the game ends. 
     * The goal is to accumulate as many points as possible by clicking or holding down the mouse, which represents the student talking. 
     * Points are displayed in real-time at the top of the game window.
     * The game is built with Java Swing components for UI elements and uses 2D graphics rendering for game characters and background. 
     * The gameplay loop is managed with a Timer to control animations and detect game-over conditions. 
     * The user interacts with the game using mouse events, with points accumulating based on how long the user can talk without getting caught.
     * After a game over, the player can click "Play Again" to restart with a fresh score.
     *
     * 
     ******** Swing Requirement ********
     * 
     * My program satisfies the requirement that there are at least 3 unique Swing components used. The components are:
     * 1. JButton (Start Game Button) - Defined in GamePanel.java at line 60. This button is used to start the game.
     * 2. JButton (Play Again Button) - Defined in GamePanel.java at line 69. This button restarts the game after a game over.
     * 3. JLabel (Points Display) - Defined in GamePanel.java at line 51. This label displays the player's score in real-time.
     * 4. JPanel (Main Game Panel) - Defined in GamePanel.java at line 11 (as the class itself). This panel draws all 2D graphics
     *    such as the player, teacher, and background.
     * 
     * 
     ******** 2D Graphics Requirement ********
     * 
     * My program satisfies the requirement of using at least 1 JPanel for drawing something.
     * This JPanel is defined in GamePanel.java at line 11 where the GamePanel class itself extends JPanel.
     * The paintComponent(Graphics g) method (defined at line 153) is overridden to draw custom graphics such as the player, 
     * teacher, background, and game over screen. This method is called regularly to update the game's visuals based on the 
     * player's actions and the game's state.
     *
     ******** Event Listener Requirement ********
     * 
     * My program satisfies the requirement of using at least one ActionListener and one MouseListener. 
     * 1. ActionListener - Defined in GamePanel.java at line 99, as the buttonAction class. 
     *    It is attached to the startButton and triggers the game to start when clicked.
     *    A second ActionListener (lambda) is attached to the restartButton at line 72, triggering restartGame().
     * 2. MouseListener - Defined in GamePanel.java at line 173, as the MouseClicked class. 
     *    This listener detects when the mouse is pressed or released, setting flags for the player's talking state.
     */

    public static void main(String[] args) {
        GamePanel g1 = new GamePanel();
    }
}
