import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadersPanel extends JPanel implements ActionListener{
	private Timer gameTimer;
	private int numClicks;
	
	public SpaceInvadersPanel(){
		super();
		gameTimer = new Timer(5, this);
		numClicks = 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		numClicks++;
	}

	
	public void checkForCollision(){
		
	}
}
