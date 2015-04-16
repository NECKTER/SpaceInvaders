import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SpaceFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpaceInvadersPanel sip;

	public SpaceFrame() {
		// TODO Auto-generated constructor stub
		super("Space Invaders!! ");
		setLayout(new BorderLayout());
		createMenus();
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sip = new SpaceInvadersPanel();
		this.add(sip);
		pack();
	}

	private void createMenus() {
		// TODO Auto-generated method stub
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(true);
		JMenu fileMenu = new JMenu("Game");
		JMenuItem newItem = new JMenuItem("Start");
		newItem.addActionListener(this);// this allows the JMenuItem called newItem to tell the Frame that someone has chosen "new"
		fileMenu.add(newItem);
		menuBar.add(fileMenu);
		//menuBar.setUI(new BasicMenuBarUI());
		setJMenuBar(menuBar);
		//add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sip.startGame();
	}

}
