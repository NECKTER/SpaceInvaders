import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadersPanel extends JPanel implements ActionListener{
	private Timer gameTimer;
	private int numClicks;
	private int dx = 1;
	
	public SpaceInvadersPanel(){
		this.setPreferredSize(new Dimension(1000,800));
		gameTimer = new Timer(5, this);
		gameTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		moveEverything();
		checkForCollision();
		repaint();
	}

	
	private void moveEverything() {
		// TODO Auto-generated method stub
		numClicks++;
	}

	public void checkForCollision(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
