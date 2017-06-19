package bb;

import bb.arena.ArenaController;
import bb.backstory.BackstoryController;
import bb.core.GameController;
import bb.core.audio.AudioFactory;
import bb.core.event.GameEvent;
import bb.core.event.GameListener;
import bb.core.view.FontFactory;
import bb.core.view.ImageFactory;
import bb.core.view.Resizer;
import bb.core.view.SpriteFactory;
import bb.title.TitleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private FontFactory fontFactory;
	private ImageFactory imageFactory;
	private SpriteFactory spriteFactory;
	private AudioFactory audioFactory;
	private GameHandler gameHandler;
	private GameController currentController;

	public BB() {
		super("Battle Balloons");
		this.fontFactory = new FontFactory();
		this.imageFactory = new ImageFactory();
		this.spriteFactory = new SpriteFactory(imageFactory);
		this.audioFactory = new AudioFactory();
		this.gameHandler = new GameHandler();
	}

	public FontFactory getFontFactory() {
		return fontFactory;
	}

	public ImageFactory getImageFactory() {
		return imageFactory;
	}

	public SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}

	public AudioFactory getAudioFactory() {
		return audioFactory;
	}

	public void launch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Forces correct size
		setContentPane(new Resizer());

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		startTitle();
	}

	public void startTitle() {
		setCurrentController(new TitleController(fontFactory, imageFactory, spriteFactory, gameHandler));
	}

	public void startBackstory() {
		setCurrentController(new BackstoryController(fontFactory, gameHandler));
	}

	public void startGame(int numPlayers) {
		setCurrentController(new ArenaController(this));
	}

	private void setCurrentController(GameController controller) {
		if (currentController != null) {
			currentController.stop();
			removeKeyListener(currentController.getKeyListener());
		}

		this.currentController = controller;
		setContentPane(new Resizer(controller.getScreen()));
		validate();
		addKeyListener(currentController.getKeyListener());
		currentController.start();
	}

	private class GameHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			Object source = event.getSource();
			String type = event.getType();

			if (type == GameEvent.START_1P_GAME) {
				startGame(1);
			} else if (type == GameEvent.START_2P_GAME) {
				startGame(2);
			} else if (type == GameEvent.SCREEN_ABORTED) {
				startTitle();
			} else if (type == GameEvent.SCREEN_EXPIRED) {
				if (source instanceof TitleController) {
					startBackstory();
				} else if (source instanceof BackstoryController) {
					startTitle();
				} else {
					throw new IllegalStateException("Unknown source: " + source);
				}
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BB().launch();
			}
		});
	}
}
