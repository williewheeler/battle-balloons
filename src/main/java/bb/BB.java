package bb;

import bb.model.GameModel;
import bb.view.arena.ArenaView;
import bb.view.FontFactory;
import bb.view.SpriteFactory;

import javax.swing.JFrame;

public class BB extends JFrame {
	private GameModel gameModel;

	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private ArenaView arenaView;

    public BB() {
    	super("Battle Balloons");
    	this.gameModel = new GameModel();
    	this.fontFactory = new FontFactory();
    	this.spriteFactory = new SpriteFactory();
    	this.arenaView = new ArenaView(gameModel, fontFactory, spriteFactory);
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
    	new BB().start();
    }
}
