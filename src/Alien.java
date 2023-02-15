import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Alien {
	Image alien = new ImageIcon("alien.png").getImage();
	
	private int xPos = 235;
	private int yPos = 425;
	private int width = 48;
    private int height = 48;
	
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
	
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void moveUp() {
    	yPos = yPos - 20;
        if (yPos < 0) {
        	yPos = 0;
        }
    }
    
    public void moveDown() {
    	yPos = yPos + 20;
        if (yPos > 454) {
        	yPos = 454;
        }
    }
    
    public void moveRight() {
    	xPos = xPos + 20;
        if (xPos > 454) {
        	xPos = 454;
        }
    }

    public void moveLeft() {
        xPos = xPos - 20;
        if (xPos < 0) {
            xPos = 0;
        }
    }
    
	public void drawAlien(Graphics g) {
		g.drawImage(alien, xPos, yPos, width, height, null);
	}
}
