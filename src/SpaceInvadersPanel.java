import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class SpaceInvadersPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer gameTimer;
	private int n;
	private int dx = 1;
	private int x;
	private int beforeMove = 5;
	private int y = 50;
	private ArrayList<SpaceObject> objects = new ArrayList<>();
	private SpriteSheet sheet = new SpriteSheet();
	private boolean needsRepaint = true;
	private SpaceObject player = new SpaceObject(500, 750, 25, 25, sheet.getPlayer());
	private ArrayList<SpaceObject> bullets = new ArrayList<>();

	public SpaceInvadersPanel() {
		setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(1000, 800));
		setBackground(Color.black);
		gameTimer = new Timer(1, this);
		setUpBindings();
		SpaceObject.panel = this;
	}

	private void setUpBindings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "Stop right");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "Stop left");
		this.getActionMap().put("shoot", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				launchWeapon();
			}
		});
		this.getActionMap().put("Stop right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("Stop left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				moveRight();
			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				moveLeft();
			}

		});
	}

	public void launchWeapon() {
		bullets.add(new SpaceObject(player.getX(), player.getY(), 15, 10, sheet.getBullet()));
	}

	public void moveRight() {
		player.move(player.getX() + 25, player.getY());
	}

	public void moveLeft() {
		player.move(player.getX() - 25, player.getY());
	}

	public void startGame() {
		for (int i = 0; i < 11; i++) {
			objects.add(new SpaceObject(x + (60 * i), y, 25, 25, sheet.getEnamy1P1()));
			objects.get(i).addImage(sheet.getEnamy1P2());
		}
		for (int i = 0; i < 11; i++) {
			objects.add(new SpaceObject(x + (60 * i), y + 50, 25, 25, sheet.getEnamy2P1()));
			objects.get(i + 11).addImage(sheet.getEnamy2P2());
		}
		for (int i = 0; i < 11; i++) {
			objects.add(new SpaceObject(x + (60 * i), y + 100, 25, 25, sheet.getEnamy2P1()));
			objects.get(i + 22).addImage(sheet.getEnamy2P2());
		}

		for (int i = 0; i < 11; i++) {
			objects.add(new SpaceObject(x + (60 * i), y + 150, 25, 25, sheet.getEnamy3P1()));
			objects.get(i + 33).addImage(sheet.getEnamy3P2());
		}
		for (int i = 0; i < 11; i++) {
			objects.add(new SpaceObject(x + (60 * i), y + 200, 25, 25, sheet.getEnamy3P1()));
			objects.get(i + 44).addImage(sheet.getEnamy3P2());
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
		if (n % beforeMove == 0) {
			x += dx;
		}
		if (n % (beforeMove * 5) == 0) {
			needsRepaint = true;
		}
	}

	public void checkForCollision() {
		if (x > this.getWidth() - 660 || x < 0) {
			dx *= -1;
			y += (this.getHeight() / 200);
		}
	}

	public void destroyEnemy(int unitNum) {
		//highest unit number is 54, lowest is 0; 55 enemies in total
		//the unit numbers go in the same order that the enemies appear in the game
	}

	private void enemyAnimation(Graphics g) {
		for (int i = 0; i < 11; i++) {
			objects.get(i).move(x + (60 * i), y);
			objects.get(i).draw(needsRepaint, g);
			objects.get(i + (11)).move(x + (60 * i), y + 50);
			objects.get(i + (11)).draw(needsRepaint, g);
			objects.get(i + (22)).move(x + (60 * i), y + 100);
			objects.get(i + (22)).draw(needsRepaint, g);
			objects.get(i + (33)).move(x + (60 * i), y + 150);
			objects.get(i + (33)).draw(needsRepaint, g);
			objects.get(i + (44)).move(x + (60 * i), y + 200);
			objects.get(i + (44)).draw(needsRepaint, g);
		}
		needsRepaint = false;
	}

	private void playerAnimation(Graphics g) {
		// TODO Auto-generated method stub
		if (gameTimer.isRunning()) {
			player.draw(g);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameTimer.isRunning()) {
			g.drawImage(sheet.getTitle(), 100, 100, this.getWidth() - 200, this.getHeight() - 200, null);
		} else {
			enemyAnimation(g);
			playerAnimation(g);
			bulletAnimation(g);
		}
	}

	private void bulletAnimation(Graphics g) {
		// TODO Auto-generated method stub
		for (SpaceObject spaceObject : bullets) {
			spaceObject.move(spaceObject.getX(), spaceObject.getY() - 1);
			spaceObject.draw(g);
		}
	}
}
