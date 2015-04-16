import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadersPanel extends JPanel implements ActionListener{
	private Timer gameTimer;
	private int n;
	private int dx = 1;
	private int x;
	private int y = 50;
	private Point[] enemyLocs; //fully functional list containing the locations of all of the enemies. you can "destroy" an enemy by simply setting their index in this array to null
	
	public SpaceInvadersPanel(){
		this.setPreferredSize(new Dimension(1000,800));
		gameTimer = new Timer(3, this);
		gameTimer.start();
		enemyLocs = new Point[55];
		for(int i = 0; i < 55; i++){
			enemyLocs[i] = (new Point(1,1));
		}
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
	
	private void enemyAnimation(Graphics g){
		int unitNum = 0;
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawString("E", x + (50 * i), y);
				enemyLocs[unitNum] = new Point(x + (50 * i), y);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawString("E", x + (50 * i), y + 50);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 50);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawString("E", x + (50 * i), y + 100);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 100);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawString("E", x + (50 * i), y + 150);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 150);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawString("E", x + (50 * i), y + 200);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 200);
			}
			unitNum++;
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		enemyAnimation(g);
	}
}
