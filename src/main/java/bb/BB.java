package bb;

import bb.model.GameModel;
import bb.view.FontFactory;
import bb.view.Resizer;
import bb.view.SpriteFactory;
import bb.view.arena.ArenaView;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BB extends JFrame {
	private GameModel gameModel;

	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private JComponent arenaView;

    public BB() {
    	super("Battle Balloons");
    	this.gameModel = new GameModel();
    	this.fontFactory = new FontFactory();
    	this.spriteFactory = new SpriteFactory();
    	this.arenaView = new Resizer(new ArenaView(gameModel, fontFactory, spriteFactory));
	}

	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setContentPane(arenaView);
    	pack();
		setResizable(false);
    	setLocationRelativeTo(null);
    	setVisible(true);
	}

    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new BB().start();
			}
		});
    }
}
