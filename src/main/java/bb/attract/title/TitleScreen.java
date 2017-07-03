package bb.attract.title;

import bb.common.BBContext;
import bb.common.view.BBScreen;
import bb.common.view.ScenePane;
import bb.framework.event.ScreenEvent;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/17/17.
 */
public class TitleScreen extends BBScreen {
	private static final String TITLE_PATH = "images/bb-title.png";
	private static final int TITLE_X = 55;
	private static final int TITLE_Y = 85;

	private BufferedImage titleImage;
	private ScenePane scenePane;

	public TitleScreen(BBContext context) {
		super(context, new TitleScene());

		this.titleImage = context.getImageLoader().loadImage(TITLE_PATH);
		this.scenePane = new ScenePane(context, getScene()) {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(titleImage, TITLE_X, TITLE_Y, titleImage.getWidth(), titleImage.getHeight(), null);
			}
		};

		setLayout(new BorderLayout());
		add(scenePane, BorderLayout.CENTER);
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyHandler();
	}

	private class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					fireScreenEvent(ScreenEvent.START_1P_GAME);
					break;
				case KeyEvent.VK_2:
					fireScreenEvent(ScreenEvent.START_2P_GAME);
					break;
			}
		}
	}
}
