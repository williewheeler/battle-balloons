package bb.attract;

import bb.BBConfig;
import bb.BBContext;
import bb.common.model.BigBalloonModel;
import bb.common.model.TextModel;
import bb.common.view.ActorViewFactory;
import bb.common.view.BigBalloonView;
import bb.common.view.TextView;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.model.Actor;
import bb.framework.model.BasicActorModel;
import bb.framework.util.MathUtil;
import bb.framework.view.AttractScreen;
import bb.framework.view.ImageView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SCREEN_HEIGHT_PX;
import static bb.BBConfig.SCREEN_WIDTH_PX;

/**
 * Created by willie on 6/17/17.
 */
public class TitleScreen extends AttractScreen {
	private static final int TTL = 15 * BBConfig.FRAMES_PER_SECOND;

	private static final String TITLE_PATH = "images/bb-title.png";
	private static final double CREATE_PROBABILITY = 0.33;
	private static final int BASE_DX = 4;

	private BufferedImage titleImage;

	public TitleScreen(BBContext context, GameListener gameListener) {
		super(context, gameListener, TTL);
		initScene();
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyHandler();
	}

	@Override
	public void updateModel() {
		super.updateModel();
		generateBalloon(0, 20, BASE_DX, 1);
		generateBalloon(SCREEN_WIDTH_PX, SCREEN_HEIGHT_PX - 20, -BASE_DX, -1);
	}

	private void initScene() {
		ActorViewFactory actorViewFactory = getContext().getActorViewFactory();

		// TODO Green
		TextModel getReadyModel = new TextModel("GET READY FOR", 55, 60);
		TextView getReadyView = actorViewFactory.createTextView(getReadyModel);
		addActor(new Actor(getReadyModel, getReadyView));

		// TODO Yellow
		TextModel playersModel = new TextModel("PRESS [1] PLAYER OR [2] PLAYERS", 55, 190);
		TextView playersView = actorViewFactory.createTextView(playersModel);
		addActor(new Actor(playersModel, playersView));

		BasicActorModel titleModel = new BasicActorModel(null, 55, 90, -1);
		titleModel.setWidth(203);
		titleModel.setHeight(69);
		ImageView titleView = actorViewFactory.createImageView(titleModel, TITLE_PATH);
		addActor(new Actor(titleModel, titleView));
	}

	private void generateBalloon(int x, int yBase, int dxBase, int dRotation) {
		if (MathUtil.nextRandomDouble() < CREATE_PROBABILITY) {
			ActorViewFactory actorViewFactory = getContext().getActorViewFactory();
			int y = generateY(yBase);
			int dx = generateDx(dxBase);
			BigBalloonModel model = new BigBalloonModel(null, x, y, dx, 0, 0, dRotation);
			BigBalloonView view = actorViewFactory.createBigBalloonView(model);
			addActor(new Actor(model, view));
		}
	}

	private int generateY(int yBase) {
		return yBase + MathUtil.nextRandomInt(15) - 7;
	}

	private int generateDx(int dxBase) {
		return dxBase + MathUtil.nextRandomInt(5) - 2;
	}

	private class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					fireGameEvent(TitleScreen.this, GameEvent.START_1P_GAME);
					break;
				case KeyEvent.VK_2:
					fireGameEvent(TitleScreen.this, GameEvent.START_2P_GAME);
					break;
			}
		}
	}
}
