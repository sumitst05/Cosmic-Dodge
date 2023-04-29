import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	// Initializing Objects
	private Alien alien = new Alien();
	private Timer timer = new Timer(1000, new TimerListener());
	private ArrayList<Asteroid> list = new ArrayList<Asteroid>();
	private Random random = new Random();
	private Boolean collision = false;

	private Image background = new ImageIcon("assets/bg.png").getImage(); // background image
	private Image gameOver = new ImageIcon("assets/gameOver.png").getImage(); // for game over prompt

	// Panel constructor
	public Panel() {
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}

	// Checking collision between Asteroids and Alien
	public void checkCollision() {
		int alienX1 = alien.getxPos();
		int alienX2 = alienX1 + alien.getWidth();
		int alienY1 = alien.getyPos();
		int alienY2 = alienY1 + alien.getHeight();
		for (Asteroid asteroid : list) {
			int asteroidX1 = asteroid.getxPos();
			int asteroidX2 = asteroidX1 + asteroid.getLato();
			int asteroidY = asteroid.getyPos();
			// Simply compare the bounds of each component
			if (((asteroidX1 > alienX1 && asteroidX1 < alienX2) || (asteroidX2 > alienX1 && asteroidX2 < alienX2))
					&& (asteroidY > alienY1 && asteroidY < alienY2)) {
				timer.stop();
				this.removeKeyListener(this); // stop
				collision = true; // collision detected
			}
		}
	}

	// Paints components
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);
		alien.drawAlien(g);
		for (Asteroid asteroid : list) {
			asteroid.drawAsteroid(g);
		}
		repaint();
		// prompt the game over image if collision is true
		if (collision == true) {
			g.drawImage(gameOver, 175, 175, 150, 150, null);
		}
	}

	// Key Listener
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			alien.moveUp();
			checkCollision();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			alien.moveDown();
			checkCollision();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			alien.moveLeft();
			checkCollision();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			alien.moveRight();
			checkCollision();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// Timer Listener to generate asteroids randomly
	public class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Integer x = random.nextInt(454);
			list.add(new Asteroid(x));
			for (Asteroid asteroid : list) {
				asteroid.moveDown();
				checkCollision(); // check collision every time
			}
		}
	}
}
