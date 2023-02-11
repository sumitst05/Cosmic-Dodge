import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {
	
	Frame() {
		this.setTitle("Game");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(new Panel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
