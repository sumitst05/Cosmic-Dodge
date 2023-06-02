import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private int timeInterval = 450;
	private int score = 0;
	private boolean collision = false;
	private boolean game_over = false;
	private boolean inMenu = true;
	private boolean isPlayButtonPressed = false;
	private boolean isQuitButtonPressed = false;
	private boolean isSettingsButtonPressed = false;
 
	// Initializing Objects
	private Alien alien = new Alien();
	private Timer timer = new Timer(timeInterval, new TimerListener());
	private ArrayList<Asteroid> list = new ArrayList<Asteroid>();
	private Random random = new Random();

	private Image logo = new ImageIcon("assets/logo.png").getImage();		// logo
	private Image background = new ImageIcon("assets/bg.png").getImage(); // background image
	private Image gameOver = new ImageIcon("assets/gameOver.png").getImage(); // for game over prompt

	// Panel constructor
	public Panel() {
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);

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

		if(!inMenu && !game_over) {
			alien.drawAlien(g);
			for (Asteroid asteroid : list) {
				asteroid.drawAsteroid(g);
			}
		}
		repaint();

		// prompt the game over image if collision is true
		if (collision) {
			g.drawImage(gameOver, 75, 150, 150, 150, null);
			game_over = true;
			inMenu = true;
		}
		
		if(inMenu && !collision) g.drawImage(logo, 4, 145, 293, 180, null);	// show game logo

		if (game_over || inMenu) {
			
			int playButtonX = getWidth() / 2 - 50;
      int playButtonY = getHeight() / 2 + 50;
			int settingsButtonX = playButtonX;
			int settingsButtonY = playButtonY + 50;
			int quitButtonX = settingsButtonX;
      int quitButtonY = settingsButtonY + 50;
			
      int ButtonWidth = 100;
      int ButtonHeight = 40;
			
			// Play Button
			if(isPlayButtonPressed) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.CYAN);
			}
			g.fillRect(playButtonX, playButtonY, ButtonWidth, ButtonHeight);
    	g.drawRect(playButtonX, playButtonY, ButtonWidth, ButtonHeight);
			g.setFont(new Font("Arial", Font.BOLD, 18));
			String playText = "Play";
			int playTextWidth = g.getFontMetrics().stringWidth(playText);

      int playTextX = playButtonX + ButtonWidth / 2 - playTextWidth / 2; // Calculate the x-coordinate for centering the text
      int playTextY = playButtonY + ButtonHeight / 2 + 5; // Adjust the y-coordinate for centering the text

			if(isPlayButtonPressed) {
				g.setColor(Color.RED);
				inMenu = false;
			}
			else {
				g.setColor(Color.BLACK);
			}
      g.drawString(playText, playTextX, playTextY);

			// Settings Button
    	if (isSettingsButtonPressed) {
    		g.setColor(Color.WHITE);
   		} else {
      	g.setColor(Color.CYAN);
    	}
    	g.fillRect(settingsButtonX, settingsButtonY, ButtonWidth, ButtonHeight);
    	g.drawRect(settingsButtonX, settingsButtonY, ButtonWidth, ButtonHeight);
    	g.setFont(new Font("Arial", Font.BOLD, 18));
    	String settingsText = "Settings";
    	int settingsTextWidth = g.getFontMetrics().stringWidth(settingsText);
    	int settingsTextX = settingsButtonX + ButtonWidth / 2 - settingsTextWidth / 2;
    	int settingsTextY = settingsButtonY + ButtonHeight / 2 + 5;

    	if (isSettingsButtonPressed) {
      	g.setColor(Color.RED);
				System.exit(0);
    	} else {
      	g.setColor(Color.BLACK);
    	}
    	g.drawString(settingsText, settingsTextX, settingsTextY);

			// Quit Button
    	if (isQuitButtonPressed) {
    		g.setColor(Color.WHITE);
   		} else {
      	g.setColor(Color.CYAN);
    	}
    	g.fillRect(quitButtonX, quitButtonY, ButtonWidth, ButtonHeight);
    	g.drawRect(quitButtonX, quitButtonY, ButtonWidth, ButtonHeight);
    	g.setFont(new Font("Arial", Font.BOLD, 18));
    	String quitText = "Quit";
    	int quitTextWidth = g.getFontMetrics().stringWidth(quitText);
    	int quitTextX = quitButtonX + ButtonWidth / 2 - quitTextWidth / 2;
    	int quitTextY = quitButtonY + ButtonHeight / 2 + 5;

    	if (isQuitButtonPressed) {
      	g.setColor(Color.RED);
				System.exit(0);
    	} else {
      	g.setColor(Color.BLACK);
    	}
    	g.drawString(quitText, quitTextX, quitTextY);
		}

		// Displays the score
  	g.setColor(Color.WHITE);
 	  g.setFont(new Font("Arial", Font.BOLD, 18));
	  String scoreText = "Score: " + score * timeInterval / 1000;
  	g.drawString(scoreText, 10, 30);
	}

	private void resetGame() {
    alien.setxPos(124);
    alien.setyPos(544);

    // Clear lists
    list.clear();

    // Initialize game state
    collision = false;
    game_over = false;
		score = 0;
    timer.start();

		this.addKeyListener(this);
		this.addMouseListener(this);
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

	// Mouse Listener
  @Override
  public void mouseClicked(MouseEvent e) {
    if (game_over) {
      // Handle menu options
    	int playButtonX = getWidth() / 2 - 50;
      int playButtonY = getHeight() / 2 + 50;
			int settingsButtonX = playButtonX;
			int settingsButtonY = playButtonY + 50;
			int quitButtonX = settingsButtonX;
      int quitButtonY = settingsButtonY + 50;

      int ButtonWidth = 100;
      int ButtonHeight = 40;
      
      if (e.getX() >= playButtonX && e.getX() <= playButtonX + ButtonWidth &&
					e.getY() >= playButtonY && e.getY() <= playButtonY + ButtonHeight) {
      	resetGame();
			} else if (e.getX() >= settingsButtonX && e.getX() <= settingsButtonX + ButtonWidth &&
					e.getY() >= settingsButtonY && e.getY() <= settingsButtonY + ButtonHeight) {
				System.exit(0);
			} else if (e.getX() >= quitButtonX && e.getX() <= quitButtonX + ButtonWidth &&
					e.getY() >= quitButtonY && e.getY() <= quitButtonY + ButtonHeight) {
				System.exit(0);
			} 	
		}
  }

	@Override
  public void mouseExited(MouseEvent e) {
  }

	@Override
  public void mouseEntered(MouseEvent e) {
	}

	@Override
  public void mousePressed(MouseEvent e) {
		int playButtonX = getWidth() / 2 - 50;
    int playButtonY = getHeight() / 2 + 50;
		int settingsButtonX = playButtonX;
		int settingsButtonY = playButtonY + 50;
		int quitButtonX = settingsButtonX;
    int quitButtonY = settingsButtonY + 50;
		
    int ButtonWidth = 100;
    int ButtonHeight = 40;

    if (e.getX() >= playButtonX && e.getX() <= playButtonX + ButtonWidth &&
        e.getY() >= playButtonY && e.getY() <= playButtonY + ButtonHeight) {
    	isPlayButtonPressed = true;
    } else if (e.getX() >= settingsButtonX && e.getX() <= settingsButtonX + ButtonWidth &&
				e.getY() >= settingsButtonY && e.getY() <= settingsButtonY + ButtonHeight) {
    	isSettingsButtonPressed = true;
		} else if (e.getX() >= quitButtonX && e.getX() <= quitButtonX + ButtonWidth &&
				e.getY() >= quitButtonY && e.getY() <= quitButtonY + ButtonHeight) {
    	isQuitButtonPressed = true;
		} 	
	}

	@Override
  public void mouseReleased(MouseEvent e) {
		int playButtonX = getWidth() / 2 - 50;
    int playButtonY = getHeight() / 2 + 50;
		int quitButtonX = playButtonX;
		int quitButtonY = playButtonY + 50;
		int settingsButtonX = quitButtonX;
		int settingsButtonY = quitButtonY + 50;

    int ButtonWidth = 100;
    int ButtonHeight = 40;

    if (e.getX() >= playButtonX && e.getX() <= playButtonX + ButtonWidth &&
        e.getY() >= playButtonY && e.getY() <= playButtonY + ButtonHeight) {
    	isPlayButtonPressed = false;
    } else if (e.getX() >= settingsButtonX && e.getX() <= settingsButtonX + ButtonWidth &&
				e.getY() >= settingsButtonY && e.getY() <= settingsButtonY + ButtonHeight) {
    	isSettingsButtonPressed = false;
		} else if (e.getX() >= quitButtonX && e.getX() <= quitButtonX + ButtonWidth &&
				e.getY() >= quitButtonY && e.getY() <= quitButtonY + ButtonHeight) {
    	isSettingsButtonPressed = false;
		} 	
	}

	// Timer Listener to generate asteroids randomly
	public class TimerListener implements ActionListener {
		private int asteroidSpacing = 3; // Adjust the spacing between asteroids
    private int counter = 0; // Counter for asteroidSpacing
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!inMenu) {
				counter++;
      	// Check if the counter has reached the desired spacing value
    		if (counter >= asteroidSpacing) {
        	Integer x = random.nextInt(227);
	      	list.add(new Asteroid(x));
        	counter = 0; // Reset the counter
      	}        
      	for (Asteroid asteroid : list) {
					checkCollision(); // check collisions everytime
        	asteroid.moveDown();
      	}
      	score++;
			}
		}
	}
}
