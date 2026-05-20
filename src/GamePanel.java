import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private int screenWidth = 1280;
    private int screenHeight = 720;
    private Player player;
    private boolean isLooking;
    private boolean isTalking;
    private MouseClicked userClicked;
    private Teacher teacher;
    private BufferedImage background;
    private boolean isGameOver;
    private GameOver gameOver;
    private boolean isRunning;
    private Timer timeSet;
    private boolean isClicking;
    private Integer PLAYER_POINT = 0;
    private JLabel points;
    private Timer timerPoint;
    private Point p;
    private JButton startButton;
    private JButton restartButton;
    private boolean gameStarted = false;

    /**
     * The GamePanel constructor initializes the JFrame, components, and game elements.
     * It sets up the GUI, loads images, and prepares the start screen.
     */
    public GamePanel() 
    {  
        JFrame GDFrame = new JFrame("Classroom Bandit"); 
        GDFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GDFrame.setSize(screenWidth, screenHeight);
        GDFrame.setResizable(false);
        GDFrame.setLocationRelativeTo(null);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setLayout(null);

        GDFrame.add(this);
        GDFrame.pack();

        // Load background image
        try {
            background = ImageIO.read(getClass().getResource("/resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Points label
        Font labelFont = new Font("Arial", Font.BOLD, 30);
        points = new JLabel("Points: 0");
        points.setFont(labelFont);
        points.setBounds(600, 10, 200, 50);
        points.setVisible(false);
        this.add(points);

        // Start button
        buttonAction ba = new buttonAction();
        startButton = new JButton("Start Game");
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setBounds(490, 270, 300, 100);
        startButton.setFont(labelFont);
        startButton.setBackground(new Color(255, 204, 204));
        startButton.setForeground(Color.BLACK);
        startButton.addActionListener(ba);
        startButton.setVisible(true);
        this.add(startButton);

        // Restart button (hidden until game over)
        restartButton = new JButton("Play Again");
        restartButton.setFocusPainted(false);
        restartButton.setBorderPainted(false);
        restartButton.setBounds(490, 500, 300, 80);
        restartButton.setFont(labelFont);
        restartButton.setBackground(new Color(204, 255, 204));
        restartButton.setForeground(Color.BLACK);
        restartButton.addActionListener(e -> restartGame());
        restartButton.setVisible(false);
        this.add(restartButton);

        player = new Player(screenWidth, screenHeight);
        teacher = new Teacher(screenWidth, screenHeight);
        gameOver = new GameOver();

        GDFrame.setVisible(true);
    }

    /**
     * buttonAction - ActionListener for the Start Game button.
     * Hides the start button, shows the points label, and starts the game loop.
     */
    public class buttonAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent a)
        {   
            startButton.setVisible(false);
            points.setVisible(true);

            userClicked = new MouseClicked();
            GamePanel.this.addMouseListener(userClicked);

            p = new Point();
            gameStarted = true;
            startGame();
            repaint();
        }
    }

    /**
     * startGame() - Initializes the game loop. Periodically checks the teacher state
     * and triggers game-over if the player is caught talking.
     */
    public void startGame()
    {
        isRunning = true; 
        isGameOver = false;

        timeSet = new Timer();
        TimerTask gameLoopTask = new TimerTask() {
            @Override
            public void run()
            {
                if (isRunning && !teacher.isWarning()) {
                    Random rand = new Random();
                    int randNum = rand.nextInt(5);

                    if (randNum == 1) {
                        teacher.startWarning();
                    }

                    isLooking = teacher.isLooking();

                    repaint();

                    if (isLooking && isTalking) {
                        isGameOver = true;
                        isRunning = false;
                        timeSet.cancel();
                        // Show restart button on the EDT
                        SwingUtilities.invokeLater(() -> restartButton.setVisible(true));
                        repaint();
                    }
                }
            }
        };

        timeSet.scheduleAtFixedRate(gameLoopTask, 500, 400);
    }

    /**
     * restartGame() - Resets all game state and starts a fresh round.
     * Called when the player clicks the "Play Again" button.
     */
    private void restartGame()
    {
        // Cancel any running timers
        if (timeSet != null) timeSet.cancel();
        if (timerPoint != null) timerPoint.cancel();

        // Reset state
        PLAYER_POINT = 0;
        points.setText("Points: 0");
        isTalking = false;
        isClicking = false;
        isLooking = false;
        isGameOver = false;

        teacher.resetLookDecision();

        // Hide restart button, keep points visible
        restartButton.setVisible(false);
        points.setVisible(true);

        // Re-register mouse listener (remove old one first to avoid duplicates)
        if (userClicked != null) {
            GamePanel.this.removeMouseListener(userClicked);
        }
        userClicked = new MouseClicked();
        GamePanel.this.addMouseListener(userClicked);

        // Restart scoring
        p = new Point();

        startGame();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {   
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, 0, 0, screenWidth, screenHeight, null);
        }

        if (!gameStarted) return;

        if (isTalking) {
            player.isTalk(g);
        } else {
            player.draw(g);
        }

        if (isGameOver) {
            teacher.caughtAct(g);
            player.isCaught(g);
            gameOver.draw(g);
        } else {
            teacher.draw(g);
        }
    }

    /**
     * MouseClicked - Handles player interaction through mouse input.
     * Sets flags for talking and clicking state.
     */
    public class MouseClicked implements MouseListener
    {   
        @Override
        public void mousePressed(MouseEvent e)
        {   
            if (!isGameOver) { 
                isTalking = true; 
                isClicking = true;
                GamePanel.this.repaint();
            }
        }

        @Override 
        public void mouseReleased(MouseEvent e)
        {
            if (!isGameOver) {
                isTalking = false;
                isClicking = false;
                GamePanel.this.repaint();
            }
        }

        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
    }

    /**
     * Point - Handles scoring logic based on the player's interaction time.
     * Awards bonus points for holding the mouse continuously for 5+ seconds.
     */
    public class Point {
        private int continuousClickSeconds = 0;
    
        public Point()
        {
            timerPoint = new Timer();
            TimerTask countingPoint = new TimerTask() {
                @Override
                public void run()
                {   
                    if (isGameOver) {
                        timerPoint.cancel();
                        return;
                    }

                    if (isClicking) {
                        continuousClickSeconds++;
                        PLAYER_POINT += (continuousClickSeconds >= 5) ? 10 : 5;
                    } else {
                        continuousClickSeconds = 0;
                    }

                    points.setText("Points: " + PLAYER_POINT.toString());
                    GamePanel.this.repaint();
                }
            };

            timerPoint.scheduleAtFixedRate(countingPoint, 0, 1000);
        }
    }
}
