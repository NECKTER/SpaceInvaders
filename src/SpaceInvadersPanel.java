import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
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
	private boolean needsImgChange = true;
	private SpaceObject player = new SpaceObject(500, 750, 25, 25, sheet.getPlayer());
	private ArrayList<SpaceObject> bullets = new ArrayList<>();
	private ArrayList<SpaceObject> toremove = new ArrayList<>();
	private double shipMove = 0;
	private int hold = 1;
	private int shipStartSpeed = 4;
	private int shipAcceleration = 100;//larger is slower
	private double lastShotTime = System.currentTimeMillis();
	private double lastShotTimeEnemy = System.currentTimeMillis();
	private int shootDelay = 0;//1000 = 1 second
	private int enemiesDestroyed = 0;
	private List<SpaceObject> destroyed = new ArrayList<SpaceObject>();
	private List<SpaceObject> enemyBullets = new ArrayList<SpaceObject>();
	private boolean shooting = false;
	private boolean right = false;
	private boolean left = false;
	private int enemyShootDelay = 750;
	int currentShooter = 0;
	int PlayerLives = 3;
	private int score = 0;
	private int[] shieldX;
	private int[] shieldY;

	//to do listbullets
	//enemy shoot
	//music
	//Obstacles

	public SpaceInvadersPanel() {
		this.setPreferredSize(new Dimension(1000, 800));
		setBackground(Color.black);
		gameTimer = new Timer(1, this);
		setUpBindings();
		SpaceObject.panel = this;
		shieldX = new int[100];
		shieldY = new int[100];
	}

	private void setUpBindings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "shootOff");
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
				shooting = true;
			}
		});
		this.getActionMap().put("shootOff", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				shooting = false;
			}
		});
		this.getActionMap().put("Stop right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				shipMove = 0;
				hold = 1;
				right = false;
			}
		});
		this.getActionMap().put("Stop left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				shipMove = 0;
				hold = 1;
				left = false;
			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				right = true;
			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				left = true;
			}

		});
	}

	public void launchWeapon() {
		double curretTime = System.currentTimeMillis();
		if ((curretTime - lastShotTime >= shootDelay) && shooting) {
			bullets.add(new SpaceObject(player.getX() + 8, player.getY(), 15, 10, sheet.getBullet()));
			lastShotTime = curretTime;
		}
	}

	public void enemyShoot(SpaceObject shooter) {
		double curretTime = System.currentTimeMillis();
		if ((curretTime - lastShotTimeEnemy >= enemyShootDelay)) {
			enemyBullets.add(new SpaceObject(shooter.getX(), shooter.getY(), 15, 10, sheet.getBullet()));
			lastShotTimeEnemy = curretTime;
			currentShooter++;
		}
	}

	public void moveShip() {
		player.move(player.getX() + shipMove, player.getY());
	}

	public void startGame() {
		objects.clear();
		bullets.clear();
		enemyBullets.clear();
		dx = 1;
		x = 0;
		y = 50;
		player.move(500, 750);
		beforeMove = 5;
		enemiesDestroyed = 0;
		destroyed.clear();
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
		if (gameOver().equals("playerHit")) {
			PlayerLives--;
			player.move(500, 750);
		}
		if (gameOver().equals("enemygone")) {
			startGame();
		}
		if (PlayerLives == 0) {
			System.out.println("Game over!");
			gameTimer.stop();
			PlayerLives = 3;
			score = 0;
		}
		for (SpaceObject spaceObject : bullets) {
			if (spaceObject.isDestroyed()) {
				bullets.remove(spaceObject);
			}
		}
		currentShooter = nextShooter();
		while (objects.get(currentShooter).isDestroyed()) {
			currentShooter = nextShooter();
		}
		if (!objects.get(currentShooter).isDestroyed()) {
			enemyShoot(objects.get(currentShooter));
		}
		for (SpaceObject bullet : bullets) {
			for (SpaceObject enemyBullet : enemyBullets) {
				if (enemyBullet.getRect().contains(bullet.getX(), bullet.getY())) {
					toremove.add(bullet);
					toremove.add(enemyBullet);
				}
			}
		}
		launchWeapon();
		moveEverything();
		checkForCollision();
		repaint();
		checkEnemiesDestroyed();
	}

	private void checkEnemiesDestroyed() {
		for (SpaceObject enemy : objects) {
			if (enemy.isDestroyed() && !destroyed.contains(enemy)) {
				destroyed.add(enemy);
				enemiesDestroyed++;
				score += 5;
			}
		}
	}

	public String gameOver() {
		for (SpaceObject bullet : enemyBullets) {
			if (player.getRect().contains(bullet.getX(), bullet.getY())) {
				enemyBullets.remove(bullet);
				return "playerHit";
			}
		}
		for (SpaceObject spaceEnemy : objects) {
			if (!spaceEnemy.isDestroyed()) {
				return "doNothing";
			}
		}
		return "enemygone";
	}

	private void moveEverything() {
		// TODO Auto-generated method stub
		n++;
		if (n % beforeMove == 0) {
			x += dx;
		}
		if (n % (beforeMove * 5) == 0) {
			needsImgChange = true;
		}
		if (enemiesDestroyed >= 10 && enemiesDestroyed < 33) {
			beforeMove = 4;
		}
		if (enemiesDestroyed >= 33 && enemiesDestroyed < 47) {
			beforeMove = 3;
		}
		if (enemiesDestroyed >= 47) {
			beforeMove = 2;
		}
		if (right && left) {
			shipMove = 0;
			hold = 1;
		}
		if (left && !right) {
			shipMove = (shipStartSpeed + (hold / shipAcceleration)) * -1;
			hold++;
		}
		if (right && !left) {
			shipMove = (shipStartSpeed + (hold / shipAcceleration));
			hold++;
		}
		moveShip();
	}

	public void checkForCollision() {
		boolean moveDown = false;
		for (SpaceObject spaceObject : objects) {
			if ((spaceObject.getX() > this.getWidth() - 25 || spaceObject.getX() < 0) && !spaceObject.isDestroyed()) {
				moveDown = true;
				break;
			}
			if (spaceObject.getY() > player.getY() && !spaceObject.isDestroyed()){
				gameTimer.stop();
			}
		}
		if (moveDown) {
			dx *= -1;
			x += dx;
			y += (25);
		}
//		if (x > this.getWidth() - 660 || x < 0) {
//			dx *= -1;
//			y += (this.getHeight() / 200);
//		}
	}

	private void enemyAnimation(Graphics g) {
		for (SpaceObject spaceObject : objects) {
			int index = objects.indexOf(spaceObject);
			int indexSubtract = 0;
			int height = y;
			if (index >= 11) {
				height += 50;
				indexSubtract += 11;
			}
			if (index >= 22) {
				height += 50;
				indexSubtract += 11;
			}
			if (index >= 33) {
				height += 50;
				indexSubtract += 11;
			}
			if (index >= 44) {
				height += 50;
				indexSubtract += 11;
			}
			spaceObject.move(x + (60 * (index - indexSubtract)), height);
			spaceObject.draw(needsImgChange, g);
		}
		needsImgChange = false;
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
			g.drawImage(sheet.getTitle(), 100, 100, this.getWidth() - 200, this.getHeight() - 200, this);
		} else {
			enemyAnimation(g);
			playerAnimation(g);
			bulletAnimation(g);
			shieldAnimation(g);
			for (int i = 0; i < PlayerLives; i++) {
				g.drawImage(sheet.getPlayer(), 0 + (i * 27), 0, 25, 25, this);
			}
			g.setColor(Color.GREEN);
			g.drawString(new String("Score: " + score), 100, 25);
		}

	}
	
	private void shieldAnimation(Graphics g) {
		// TODO Auto-generated method stub
		g.drawPolygon(shieldX, shieldY, shieldX.length);
	}

	private void bulletAnimation(Graphics g) {
		// TODO Auto-generated method stub
		for (SpaceObject spaceObject : bullets) {
			spaceObject.move(spaceObject.getX(), spaceObject.getY() - 5);
			if (spaceObject.getY() < 0) toremove.add(spaceObject);
			spaceObject.draw(g);
			for (SpaceObject spaceEnemy : objects) {
				if (!spaceEnemy.isDestroyed() && spaceEnemy.getRect().contains(spaceObject.getX(), spaceObject.getY())) {
					toremove.add(spaceObject);
					spaceEnemy.destroy();
				}
			}

		}
		for (SpaceObject spaceObject : enemyBullets) {
			spaceObject.move(spaceObject.getX(), spaceObject.getY() + 5);
			spaceObject.draw(g);
		}
		enemyBullets.removeAll(toremove);
		bullets.removeAll(toremove);
		toremove.clear();
	}

	private int nextShooter() {
		int temp = (int) (Math.random() * 55);
		while (temp > 54)
			temp = (int) (Math.random() * 55);
		return temp;

	}
}
