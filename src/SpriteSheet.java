import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage main;
	private Image Enamy1P1;
	private Image Enamy1P2;
	private Image Enamy2P1;
	private Image Enamy2P2;
	private Image Enamy3P1;
	private Image Enamy3P2;
	private Image EnamyDeath;
	private Image Player;
	private Image PlayerDeath;
	private Image Title;
	private Image bullet;
	private Image LargeEnemy;

	public SpriteSheet() {
		try {
			main = ImageIO.read(SpriteSheet.class.getResourceAsStream("images/SpriteSheet.png"));
		} catch (IOException e) {
		}
		Title = main.getSubimage(0, 0, 228, 151);
		Enamy1P1 = main.getSubimage(229, 0, 292 - 229, 63);
		Enamy1P2 = main.getSubimage(295, 0, 358 - 295, 63);
		Enamy2P1 = main.getSubimage(229, 66, 316 - 229, 129 - 66);
		Enamy2P2 = main.getSubimage(319, 66, 406 - 319, 129 - 66);
		Enamy3P1 = main.getSubimage(361, 0, 456 - 361, 63);
		Enamy3P2 = main.getSubimage(475, 0, 570 - 475, 63);
		EnamyDeath = main.getSubimage(284, 154, 387 - 284, 217 - 154);
		Player = main.getSubimage(178, 154, 281 - 178, 217 - 154);
		bullet = main.getSubimage(573, 0, 596 - 573, 47);
		LargeEnemy = main.getSubimage(409, 66, 600 - 409, 149 - 66);
	}

	public Image getEnamy1P1() {
		return Enamy1P1;
	}

	public Image getEnamy1P2() {
		return Enamy1P2;
	}

	public Image getEnamy2P1() {
		return Enamy2P1;
	}

	public Image getEnamy2P2() {
		return Enamy2P2;
	}

	public Image getEnamy3P1() {
		return Enamy3P1;
	}

	public Image getEnamy3P2() {
		return Enamy3P2;
	}

	public Image getBullet() {
		return bullet;
	}

	public Image getEnamyDeath() {
		return EnamyDeath;
	}

	public Image getLargeEnemy() {
		return LargeEnemy;
	}

	public BufferedImage getMain() {
		return main;
	}

	public Image getPlayer() {
		return Player;
	}

	public Image getPlayerDeath() {
		return PlayerDeath;
	}

	public Image getTitle() {
		return Title;
	}
}
