package com.williewheeler.battleballoons.attract;

import com.williewheeler.battleballoons.attract.world.BackstoryScene;
import com.williewheeler.battleballoons.attract.world.RosterScene;
import com.williewheeler.battleballoons.attract.view.TitleScreen;
import com.williewheeler.battleballoons.common.BBConfig;
import com.williewheeler.battleballoons.common.view.BBViewContext;
import com.williewheeler.battleballoons.common.view.AbortableSceneScreen;
import com.williewheeler.battleballoons.common.view.TransitionScreen;
import io.halfling.view.event.ScreenEvent;
import io.halfling.view.event.ScreenListener;
import io.halfling.mode.AbstractMode;
import io.halfling.view.Screen;
import io.halfling.view.ScreenBuilder;
import io.halfling.view.ScreenManager;
import io.halfling.core.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willie on 7/1/17.
 */
public class AttractMode extends AbstractMode {
	private BBConfig config;
	private BBViewContext context;
	private List<ScreenBuilder> screenBuilders = new ArrayList<>();
	private int currScreenIndex = -1;
	private ScreenHandler screenHandler;

	public AttractMode(BBConfig config, BBViewContext context, ScreenManager screenManager) {
		super(BBConfig.ATTRACT_MODE, screenManager);
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		this.config = config;
		this.context = context;
		initScreenBuilders();
		initListeners();
	}
	
	@Override
	public void start() {
		transitionTo(0);
	}
	
	@Override
	public void transitionTo(Screen screen) {
		screen.addScreenListener(screenHandler);
		super.transitionTo(screen);
	}
	
	private void initScreenBuilders() {
		screenBuilders.add(new ScreenBuilder() {
			@Override
			public Screen build() {
				return TransitionScreen.create(AttractScreenNames.TRANSITION_SCREEN, config, context);
			}
		});
		screenBuilders.add(new ScreenBuilder() {
			@Override
			public Screen build() {
				return TitleScreen.create(config, context);
			}
		});
		screenBuilders.add(new ScreenBuilder() {
			@Override
			public Screen build() {
				return AbortableSceneScreen.create(
						AttractScreenNames.BACKSTORY_SCREEN,
						config,
						context,
						new BackstoryScene());
			}
		});
		screenBuilders.add(new ScreenBuilder() {
			@Override
			public Screen build(){
				return AbortableSceneScreen.create(
						AttractScreenNames.ROSTER_SCREEN,
						config,
						context,
						new RosterScene());
			}
		});
	}
	
	private void initListeners() {
		this.screenHandler = new ScreenHandler();
	}
	
	private void transitionTo(int index) {
		this.currScreenIndex = index;
		transitionTo(screenBuilders.get(currScreenIndex).build());
	}

	private class ScreenHandler implements ScreenListener {

		@Override
		public void handleEvent(ScreenEvent event) {
			final int numScreens = screenBuilders.size();
			
			switch (event.getType()) {
				case START_1P_GAME:
				case START_2P_GAME:
					getCurrentScreen().stop();
					stop();
					break;
				case PREVIOUS_SCREEN:
					getCurrentScreen().stop();
					transitionTo((currScreenIndex + numScreens - 1) % numScreens);
					break;
				case NEXT_SCREEN:
				case SCREEN_EXPIRED:
					getCurrentScreen().stop();
					transitionTo((currScreenIndex + 1) % numScreens);
					break;
				case SCREEN_ABORTED:
					getCurrentScreen().stop();
					start();
					break;
			}
		}
	}
}
