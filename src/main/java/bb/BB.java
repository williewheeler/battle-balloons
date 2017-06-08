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
	private Resizer resizer;

	public BB() {
		super("Battle Balloons");
		this.gameModel = new GameModel();
		this.fontFactory = new FontFactory();
		this.spriteFactory = new SpriteFactory();
		this.arenaView = new ArenaView(gameModel, fontFactory, spriteFactory);
		this.resizer = new Resizer();
	}

	public void start() {
		resizer.add(arenaView);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(resizer);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BB().start();
			}
		});
	}
}
