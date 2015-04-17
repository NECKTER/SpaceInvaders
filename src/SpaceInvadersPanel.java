import java.awt.Color;
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
	private Point[] enemyLocs; //destroy an enemy by setting their index in this array to null
	private SpriteSheet sheet = new SpriteSheet();
	
	public SpaceInvadersPanel(){
		this.setPreferredSize(new Dimension(1000,800));
		setBackground(Color.black);
		gameTimer = new Timer(3, this);
		enemyLocs = new Point[55];
	}
	
	public void startGame(){
		for(int i = 0; i < 55; i++){
			enemyLocs[i] = (new Point(1,1));
		}
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
	
	public void destroyEnemy(int unitNum){
		//highest unit number is 54, lowest is 0; 55 enemies in total
		//the unit numbers go in the same order that the enemies appear in the game
		enemyLocs[unitNum] = null;
	}
	
	private void enemyAnimation(Graphics g){
		g.setColor(Color.GREEN);
		int unitNum = 0;
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawImage(sheet.getEnamy1P1(), x + (50 * i), y, 25, 25, this);
				enemyLocs[unitNum] = new Point(x + (50 * i), y);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawImage(sheet.getEnamy1P1(), x + (50 * i), y + 50, 25, 25, this);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 50);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawImage(sheet.getEnamy1P1(), x + (50 * i), y + 100, 25, 25, this);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 100);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawImage(sheet.getEnamy1P1(), x + (50 * i), y + 150, 25, 25, this);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 150);
			}
			unitNum++;
		}
		for (int i = 0; i < 11; i++){
			Point current = enemyLocs[unitNum];
			if (current != null){
				g.drawImage(sheet.getEnamy1P1(), x + (50 * i), y + 200, 25, 25, this);
				enemyLocs[unitNum] = new Point(x + (50 * i), y + 200);
			}
			unitNum++;
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		enemyAnimation(g);
		if(!gameTimer.isRunning())g.drawImage(sheet.getTitle(), 100, 100, this.getWidth()-200, this.getHeight()-200, null);
	}
}
