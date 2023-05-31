import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Asteroid {
    private int xPos;
    private int yPos = -20;
    private int lato = 35; // for size of asteroid

    Image asteroid = new ImageIcon("assets/asteroid.png").getImage();

    // Constructor
    public Asteroid(int xPos) {
        super();
        this.xPos = xPos;
    }

    // Getters and Setters
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getLato() {
        return lato;
    }

    public void setLato(int lato) {
        this.lato = lato;
    }

    public void moveDown() {
        yPos += 40;
    }

    // Draw asteroid
    public void drawAsteroid(Graphics g) {
        g.drawImage(asteroid, xPos, yPos, lato, lato, null);
    }
}
