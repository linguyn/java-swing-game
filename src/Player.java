import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Player extends DrawCharacter {
    private int x; 
    private int y; 
    private int width; 
    private int height; 
    BufferedImage notTalk;
    BufferedImage talk;
    BufferedImage getCaught;

    /**
     * The Player class represents the player character in the game.
     * It handles the player's appearance when they are talking, idle, or caught by the teacher.
     * It extends the DrawCharacter class and overrides the draw() method for rendering.
     */
    public Player(int screenWidth, int screenHeight)
    {   
        this.x = (int) (screenWidth * 0.0026);   
        this.y = (int) (screenHeight * -0.0093);  
        this.width = (int) (screenWidth * 0.994) + 5;  
        this.height = (int) (screenHeight * 1.0185);
        try 
        {
            notTalk   = ImageIO.read(getClass().getResource("/resources/PlayerBack.png"));
            talk      = ImageIO.read(getClass().getResource("/resources/Talking.png"));
            getCaught = ImageIO.read(getClass().getResource("/resources/sad.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * draw() - Draws the player in their default state (not talking).
     * @param g The Graphics object used for rendering.
     */
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(notTalk, x, y, width, height, null);
    }

    /**
     * isTalk() - Draws the player when they are talking.
     * @param g The Graphics object used for rendering.
     */
    public void isTalk(Graphics g)
    {
        g.drawImage(talk, x, y, width, height, null);
    }

    /**
     * isCaught() - Draws the player when they have been caught by the teacher.
     * @param g The Graphics object used for rendering.
     */
    public void isCaught(Graphics g)
    {
        g.drawImage(getCaught, x, y, width, height, null);
    }
}
