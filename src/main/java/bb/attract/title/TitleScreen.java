package bb.attract.title;

import bb.attract.AttractScreenNames;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.actor.view.ActorViewFactory;
import bb.common.scene.BBScenePane;
import bb.common.screen.SceneScreen;
import bb.framework.event.ScreenEvent;
import bb.framework.util.Assert;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/17/17.
 */
public class TitleScreen extends SceneScreen {
	private static final String TITLE_PATH = "images/bb-title.png";
	private static final int TITLE_X = 55;
	private static final int TITLE_Y = 85;

	public static TitleScreen create(BBConfig config, BBContext context) {
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		TitleScreen screen = new TitleScreen(config, context);
		screen.postConstruct();
		return screen;
	}

	private TitleScreen(BBConfig config, BBContext context) {
		super(AttractScreenNames.TITLE_SCREEN, config, context, new TitleScene());
	}
	
	@Override
	public JComponent buildJComponent() {
		BBContext context = (BBContext) getContext();
		ActorViewFactory avf = context.getActorViewFactory();
		BufferedImage titleImage = context.getImageLoader().loadImage(TITLE_PATH);

		return new BBScenePane(avf, getScene()) {

			@Override
			public Dimension getPreferredSize() {
				return BBConfig.SCREEN_SIZE_PX;
			}

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(titleImage, TITLE_X, TITLE_Y, null);
			}
		};
	}

	@Override
	public KeyListener buildKeyHandler() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				switch (e.getKeyCode()) {
					case KeyEvent.VK_1:
						fireScreenEvent(ScreenEvent.Type.START_1P_GAME);
						break;
					case KeyEvent.VK_2:
						fireScreenEvent(ScreenEvent.Type.START_2P_GAME);
						break;
					case KeyEvent.VK_LEFT:
						fireScreenEvent(ScreenEvent.Type.PREVIOUS_SCREEN);
						break;
					case KeyEvent.VK_RIGHT:
						fireScreenEvent(ScreenEvent.Type.NEXT_SCREEN);
						break;
				}
			}
		};
	}
}
