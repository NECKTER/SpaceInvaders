import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadersPanel extends JPanel implements ActionListener{
	private Timer gameTimer;
	private int n;
	private int dx = 1;
	private int dy = 1;
	private int x;
	private int y = 50;
	
	public SpaceInvadersPanel(){
		this.setPreferredSize(new Dimension(1000,800));
		gameTimer = new Timer(3, this);
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
		n++;
		if(n %5==0) {
			x += dx;
		}
	}

	public void checkForCollision(){
		if(x > this.getWidth() - 525 || x < 0){
			dx *= -1;
			y += (this.getHeight()/200);
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < 11; i++){
			g.drawString("E", x + (50 * i), y);
		}
		for (int i = 0; i < 11; i++){
			g.drawString("E", x + (50 * i), y + 50);
		}
		for (int i = 0; i < 11; i++){
			g.drawString("E", x + (50 * i), y + 100);
		}
		for (int i = 0; i < 11; i++){
			g.drawString("E", x + (50 * i), y + 150);
		}
		for (int i = 0; i < 11; i++){
			g.drawString("E", x + (50 * i), y + 200);
		}
	}
}
