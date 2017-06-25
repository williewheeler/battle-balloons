package bb.common.view.actor;

import bb.common.model.LexiModel;
import bb.framework.view.actor.AbstractActorView;

import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/24/17.
 */
public class LexiView extends AbstractActorView {
	private BufferedImage[] walkingSprites;
	private BufferedImage[] blinkingSprites;
	private BufferedImage[] wavingSprites;

	public LexiView(LexiModel model) {
		super(model);
	}

	public void setWalkingSprites(BufferedImage[] walkingSprites) {
		this.walkingSprites = walkingSprites;
	}

	public void setBlinkingSprites(BufferedImage[] blinkingSprites) {
		this.blinkingSprites = blinkingSprites;
	}

	public void setWavingSprites(BufferedImage[] wavingSprites) {
		this.wavingSprites = wavingSprites;
	}

	@Override
	public BufferedImage getCurrentSprite() {

		// TODO Use generics?
		LexiModel model = (LexiModel) getModel();

		int index = -1;
		switch (model.getState()) {
			case BLINKING:
				index = (model.getEyesOpen() ? 0 : 1);
				return blinkingSprites[index];
			case WALKING:
				return getWalkingSprite();
			case WAVING:
				index = (model.getWavingLeft() ? 0 : 1);
				return wavingSprites[index];
			default:
				return null;
		}
	}

	private BufferedImage getWalkingSprite() {

		// TODO Use generics?
		LexiModel model = (LexiModel) getModel();

		int baseIndex = 0;
		switch (model.getDirection()) {
			case UP:
				baseIndex = 0;
				break;
			case DOWN:
				baseIndex = 4;
				break;
			case UP_LEFT:
			case LEFT:
			case DOWN_LEFT:
				baseIndex = 8;
				break;
			case UP_RIGHT:
			case RIGHT:
			case DOWN_RIGHT:
				baseIndex = 12;
				break;
			default:
				baseIndex = 4;
				break;
		}

		int offset = model.getWalkCounter();
		int index = baseIndex + offset;

		return walkingSprites[index];
	}
}
