import java.awt.Graphics;
import java.util.ArrayList;

public class Painter extends Thread {
	private Graphics g;
	private ArrayList<SpaceObject> list;
	private boolean running = true;
	private boolean change = false;

	public Painter(Graphics g, ArrayList<SpaceObject> list) {
		// TODO Auto-generated constructor stub
		this.g = g;
		this.list = list;
	}

	@Override
	public void run() {
		int i = 0;
		while (running) {
			if (i%100 == 0) {
				change = true;
				i = 0;
			}
			for (SpaceObject spaceObject : list) {
				spaceObject.draw(change);
			}
			if (change) {
			change = false;
			}
			i++;
		}
	}

	public void update(ArrayList<SpaceObject> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		running = false;
	}
}
