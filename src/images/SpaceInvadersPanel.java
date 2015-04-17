import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class SpaceInvadersPanel extends JPanel implements ActionListener {

	private static final int DEF_START_X = 100;
	private static final int DEF_START_Y = 700;
	DefenderShip defShip = new DefenderShip();
	List<AlienShip> aliens = new ArrayList<AlienShip>();
	List<Bullet> alienBullets = new ArrayList<Bullet>(),
			     defenderBullets = new ArrayList<Bullet>();
	
	List<Shield> shieldList = new ArrayList<Shield>();
	
	int x, y;
	int numClicks = 0;
	Timer gameTimer;
	private int dx=1;
	private double dy =1.5;
	private int circleX;
	private int circleY;
	
	public SpaceInvadersPanel() {
		this.setPreferredSize(new Dimension(1000,800));
		gameTimer = new Timer(10, this);
		setUpKeyBindings();
		setUpDefender();
		setUpAliens();
		
		//gameTimer.start();
	}

	private void setUpAliens() {
		
		
	}

	private void setUpDefender() {
		this.defShip.setLocation(DEF_START_X, DEF_START_Y);
		
	}

	private void setUpKeyBindings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
				"fire");
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
				"right");
		this.getActionMap().put("fire",
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						launchWeapon();
					}
		});
		this.getActionMap().put("right",
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setDefenderDir(1);// 1 moves right, 0 moves left
					}
			
		});

	}

	protected void setDefenderDir(int i) {
		if(i == 0) {
			System.out.println("Ship will move left");
		}
		else if(i == 1) {
			System.out.println("Ship will move right");
		}
			
	}

	protected void launchWeapon() {
		System.out.println("Launching weapon now!!");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// what do you want to do every 10th of a second?
		System.out.println("just clicked! "+numClicks);
		moveEverything();
		checkForCollision();
		repaint();
	}
	private void checkForCollision() {
	
	}

	private void moveEverything() {
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

	public void start() {
		System.out.println("Just started a new game...");
		this.gameTimer.start();
		
	}
	
	
}
