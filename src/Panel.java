import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener {
	private Alien alien = new Alien();
	private Timer timer = new Timer(1000, new TimerListener());
    private ArrayList<Asteroid> list = new ArrayList<Asteroid>();
    private Random random = new Random();
	
    Image background = new ImageIcon("bg.png").getImage();
    
	Panel() {
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}
	
	public void checkCollision() {
		int alienX1 = alien.getxPos();
		int alienX2 = alienX1 + alien.getWidth();
		int alienY1 = alien.getyPos();
		int alienY2 = alienY1 + alien.getHeight();
		for(Asteroid asteroid : list) {
			int asteroidX1 = asteroid.getxPos();
			int asteroidX2 = asteroidX1 + asteroid.getLato();
			int asteroidY1 = asteroid.getyPos();
			int asteroidY2 = asteroidY1 + asteroid.getLato();
			if(((asteroidX1 >= alienX1 && asteroidX1 <= alienX2) || (asteroidX2 >= alienX1 && asteroidX2 <= alienX2))
				&& (asteroidY1 >= alienY1  && asteroidY1 <= alienY2)) { 
				timer.stop();
				this.removeKeyListener(this);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);
		alien.drawAlien(g);
		for (Asteroid asteroid : list) {
            asteroid.drawAsteroid(g);
        }
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			alien.moveUp();
			checkCollision();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			alien.moveDown();
			checkCollision();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			alien.moveLeft();
			checkCollision();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			alien.moveRight();
			checkCollision();
		}
	}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
	
	public class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Integer x = random.nextInt(454);
            list.add(new Asteroid(x));
            for(Asteroid asteroid : list) {
                asteroid.moveDown();
                checkCollision();
            }
		}
	}
}
