import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class SpaceObject {
	public static SpaceInvadersPanel panel;
	private Rectangle myrect;
	private int x, y, h, w;
	private Image img;
	private Image img2;
	private boolean canAnimate = false;
	private int animation = 0;
	private boolean changeImg = true;

	public SpaceObject(int x, int y, int h, int w, Image img) {
		this.h = h;
		this.img = img;
		this.w = w;
		this.x = x;
		this.y = y;
	}

	public void addImage(Image img) {
		// TODO Auto-generated method stub
		this.img2 = img;
		canAnimate = true;
	}

	public void draw(boolean changeImg, Graphics g) {
		this.changeImg = changeImg;
		g.drawImage(getImage(), x, y, w, h, panel);
		this.changeImg = true;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(getImage(), x, y, w, h, panel);
	}

	public void move(int x, int y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}

	private Image getImage() {
		// TODO Auto-generated method stub
		if (canAnimate) {
			if (animation == 0) {
				if (changeImg) {
					animation++;
				}
				return img;
			}
			if (changeImg) {
				animation--;
			}
			return img2;
		}
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void shoot() {
		// TODO Auto-generated method stub
		
	}
}
