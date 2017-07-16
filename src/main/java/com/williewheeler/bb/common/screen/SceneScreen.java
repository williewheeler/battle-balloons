package com.williewheeler.bb.common.screen;

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.BBContext;
import com.williewheeler.bb.common.actor.view.ActorViewFactory;
import com.williewheeler.bb.common.scene.BBScene;
import com.williewheeler.bb.common.scene.BBScenePane;
import com.williewheeler.retroge.event.ScreenEvent;
import com.williewheeler.retroge.screen.AbstractScreen;
import com.williewheeler.retroge.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by willie on 7/3/17.
 */
public abstract class SceneScreen extends AbstractScreen {
	private static final Logger log = LoggerFactory.getLogger(SceneScreen.class);
	
	private final BBScene scene;

	public SceneScreen(String name, BBConfig config, BBContext context, BBScene scene) {
		super(name, config, context);
		Assert.notNull(scene, "scene can't be null");
		this.scene = scene;
	}
	
	public BBScene getScene() {
		return scene;
	}
	
	/**
	 * By default, the screen is a scene pane. Override as desired.
	 *
	 * @return
	 */
	@Override
	public JComponent buildJComponent() {
		BBContext context = (BBContext) getContext();
		ActorViewFactory avf = context.getActorViewFactory();
		return new BBScenePane(avf, scene) {

			@Override
			public Dimension getPreferredSize() {
				return BBConfig.SCREEN_SIZE_PX;
			}
		};
	}

	@Override
	public ActionListener buildTimerHandler() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (scene.isActive()) {
					scene.update();
					repaint();
				} else {
					fireScreenEvent(ScreenEvent.Type.SCREEN_EXPIRED);
				}
			}
		};
	}
	
	@Override
	public void start() {
		super.start();
	}
}
