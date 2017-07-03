package bb.attract.roster;

import bb.common.BBContext;
import bb.common.screen.BBScreen;
import bb.common.scene.ScenePane;
import bb.framework.event.ScreenEvent;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/30/17.
 */
public class RosterScreen extends BBScreen {
	private ScenePane scenePane;

	public RosterScreen(BBContext context) {
		super(context, new RosterScene());

		this.scenePane = new ScenePane(context, getScene());

		setLayout(new BorderLayout());
		add(scenePane, BorderLayout.CENTER);
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireScreenEvent(ScreenEvent.SCREEN_ABORTED);
			}
		};
	}
}
