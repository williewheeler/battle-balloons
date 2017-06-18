package bb;

import bb.arena.ArenaController;
import bb.core.GameController;
import bb.core.audio.AudioFactory;
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
	private GameController currentController;

	public BB() {
		super("Battle Balloons");
		this.fontFactory = new FontFactory();
		this.imageFactory = new ImageFactory();
		this.spriteFactory = new SpriteFactory(imageFactory);
		this.audioFactory = new AudioFactory();
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
		setCurrentController(new TitleController(this));
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BB().launch();
			}
		});
	}
}
