import java.awt.*;
import java.io.IOException;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Teacher extends DrawCharacter {
    private int x;
    private int y;
    private int width; 
    private int height;
    BufferedImage teacherLook; 
    BufferedImage caughtYou;
    BufferedImage[] teacherBackMotion;
    int frameTracker = 0;
    private boolean isWarning = false;
    private boolean willLook = false;
    private boolean isLooking = false;
    private boolean teacherCooldown = false;
    private Timer changePosTimer;

    /**
     * Constructor - Initializes the teacher's position, size, images, and animation logic.
     */
    public Teacher(int screenWidth, int screenHeight)
    {   
        this.x = (int) (screenWidth * 0.4167);   
        this.y = (int) (screenHeight * 0.4167);  
        this.width = (int) (screenWidth * 0.2604); 
        this.height = (int) (screenHeight * 0.4629); 
        try {
            teacherBackMotion = new BufferedImage[4];
            teacherBackMotion[0] = ImageIO.read(getClass().getResource("/resources/TeacherBack.png"));
            teacherBackMotion[1] = ImageIO.read(getClass().getResource("/resources/TeacherBack2.png"));
            teacherBackMotion[2] = ImageIO.read(getClass().getResource("/resources/TeacherBack3.png"));
            teacherBackMotion[3] = ImageIO.read(getClass().getResource("/resources/TeacherBack4.png"));
            teacherLook = ImageIO.read(getClass().getResource("/resources/TeacherLook.png"));
            caughtYou   = ImageIO.read(getClass().getResource("/resources/Caught.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        startAnimationTimer();
    }

    /**
     * startAnimationTimer() - Starts the timer that cycles through back-facing animation frames.
     */
    private void startAnimationTimer() {
        TimerTask changePos = new TimerTask() {
            @Override
            public void run() {
                if (!isWarning && !isLooking) { 
                    frameTracker = (frameTracker == 3) ? 0 : frameTracker + 1;
                }
            }
        };
        changePosTimer = new Timer();
        changePosTimer.scheduleAtFixedRate(changePos, 0, 500);
    }

    /**
     * startWarning() - Initiates the warning state where the teacher becomes suspicious.
     * After 1 second, randomly decides whether to turn and look at the player.
     */
    public void startWarning()
    {
        if (!teacherCooldown) {
            isWarning = true;
            willLook = false;

            Timer warningTimer = new Timer();
            warningTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Random rand = new Random();
                    willLook = rand.nextInt(100) < 60;

                    if (willLook) {
                        isLooking = true;
                        isWarning = false;
                    } else {
                        isLooking = false;
                        isWarning = false;
                    }
                }
            }, 1000);
        }
    }

    /**
     * draw(Graphics g) - Renders the teacher's current visual state.
     * @param g The Graphics object used for rendering.
     */
    @Override
    public void draw(Graphics g)
    {   
        if (isWarning) {
            g.drawImage(teacherBackMotion[3], x, y, width, height, null);
        } else if (isLooking) {
            g.drawImage(teacherLook, x, y, width, height, null);
        } else {
            g.drawImage(teacherBackMotion[frameTracker], x, y, width, height, null);
        }
    }

    /**
     * caughtAct(Graphics g) - Draws the teacher's caught image when the player is caught.
     * @param g The Graphics object used for rendering.
     */
    public void caughtAct(Graphics g)
    {
        g.drawImage(caughtYou, x, y, width, height, null);
    }

    /**
     * resetLookDecision() - Resets all teacher state flags (used when restarting the game).
     */
    public void resetLookDecision() {
        isWarning = false;
        isLooking = false;
        willLook = false;
    }

    public boolean isLooking() { return isLooking; }
    public boolean isWarning() { return isWarning; }
}
