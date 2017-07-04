package bb.common.screen;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.scene.BBScene;
import bb.framework.event.ScreenEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 7/3/17.
 */
public class AbortableSceneScreen extends SceneScreen {

	public AbortableSceneScreen(String name, BBConfig config,  BBContext context, BBScene scene) {
		super(name, config, context, scene);
	}

	@Override
	public KeyListener buildKeyHandler() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireScreenEvent(ScreenEvent.SCREEN_ABORTED);
			}
		};
	}
}
