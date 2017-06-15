package bb;

import bb.audio.AudioFactory;
import bb.input.KeyboardManager;
import bb.model.GameModel;
import bb.model.event.GameEvent;
import bb.model.event.GameEvents;
import bb.model.event.GameListener;
import bb.view.FontFactory;
import bb.view.Resizer;
import bb.view.SpriteFactory;
import bb.view.arena.ArenaView;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bb.BBConfig.FRAME_PERIOD_MS;

public class BB extends JFrame implements ActionListener, GameListener {
	private GameModel gameModel;

	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private JComponent arenaView;
	private Resizer resizer;
	private KeyboardManager keyboardManager;
	private AudioFactory audioFactory;
	private Timer timer;

	public BB() {
		super("Battle Balloons");
		this.gameModel = new GameModel();
		this.fontFactory = new FontFactory();
		this.spriteFactory = new SpriteFactory();
		this.arenaView = new ArenaView(gameModel, fontFactory, spriteFactory);
		this.resizer = new Resizer();
		this.keyboardManager = new KeyboardManager(gameModel.getPlayer());
		this.audioFactory = new AudioFactory();
		this.timer = new Timer(FRAME_PERIOD_MS, this);
		
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(keyboardManager);
		
		gameModel.addGameListener(this);
	}

	public void start() {
		resizer.add(arenaView);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(resizer);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameModel.update();
		resizer.repaint();
	}
	
	@Override
	public void handleEvent(GameEvent event) {
		if (event == GameEvents.PLAYER_WALKS) {
			audioFactory.playerWalks();
		} else if (event == GameEvents.PLAYER_COLLISION) {
			audioFactory.playerCollision();
		} else if (event == GameEvents.PLAYER_THROWS_BALLOON) {
			audioFactory.playerThrowsBalloon();
		} else if (event == GameEvents.JUDO_HIT) {
			audioFactory.judoHit();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BB().start();
			}
		});
	}
}
