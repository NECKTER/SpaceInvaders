import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class SpaceFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MenuBar bar = new MenuBar();
	Menu file = new Menu("File");
	public SpaceFrame(SpaceInvadersPanel sip) {
		// TODO Auto-generated constructor stub
		super("Space Invaders");
		this.add(sip);
		bar.add(file);
		file.add("new");
		this.pack();
		this.validate();
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
