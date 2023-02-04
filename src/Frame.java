import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements KeyListener {
	JLabel alien;
	JLabel background;
	
	Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLayout(null);
		this.addKeyListener(this);
		
		background = new JLabel(new ImageIcon("bg.png"));
		background.setBounds(0, 0, 500, 500);
		alien = new JLabel(new ImageIcon("alien.png"));
		alien.setBounds(225, 225, 50, 50);
		
		this.getContentPane().setBackground(Color.black);
		this.add(alien);
		this.add(background);
		this.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'a' : alien.setLocation(alien.getX() - 10, alien.getY());
			break;
		case 'w' : alien.setLocation(alien.getX(), alien.getY() - 10);
			break;
		case 's' : alien.setLocation(alien.getX(), alien.getY() + 10);
			break;
		case 'd' : alien.setLocation(alien.getX() + 10, alien.getY());
			break;
		}	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37 : alien.setLocation(alien.getX() - 10, alien.getY());
			break;
		case 38 : alien.setLocation(alien.getX(), alien.getY() - 10);
			break;
		case 39 : alien.setLocation(alien.getX() + 10, alien.getY());
			break;
		case 40 : alien.setLocation(alien.getX(), alien.getY() + 10);
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
}
