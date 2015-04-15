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
	private int dy = 1;
	private int x;
	private int y;
	
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
		if(numClicks %5==0) {
			x += dx;
			y += dy;
		}
		if(numClicks %500 == 0) {
			y/=2;
		}
	}

	public void checkForCollision(){
		if(x > this.getWidth() || x < 0){
			dx *= -1;
		}
		if(y > this.getHeight() || y < 0){
			dy *= -1;
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString(""+numClicks, x, y);
	}
}
