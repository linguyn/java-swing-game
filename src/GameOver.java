import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GameOver {
    BufferedImage overImage; 

    public GameOver()
    {   
        try {
            overImage = ImageIO.read(getClass().getResource("/resources/GameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g)
    {   
        g.drawImage(overImage, 340, 130, 600, 300, null);
    }
}
