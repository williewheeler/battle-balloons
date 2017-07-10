package bb.attract;

import bb.attract.backstory.BackstoryScene;
import bb.attract.roster.RosterScene;
import bb.attract.title.TitleScreen;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.screen.AbortableSceneScreen;
import bb.common.screen.TransitionScreen;
import bb.framework.event.ScreenEvent;
import bb.framework.event.ScreenListener;
import bb.framework.mode.AbstractMode;
import bb.framework.screen.Screen;
import bb.framework.screen.ScreenBuilder;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static bb.attract.AttractScreenNames.*;

/**
 * Created by willie on 7/1/17.
 */
public class AttractMode extends AbstractMode {
	private BBConfig config;
	private BBContext context;
	private List<ScreenBuilder> screenBuilders = new ArrayList<>();
	private int currScreenIndex = -1;
	private ScreenHandler screenHandler;

	public AttractMode(BBConfig config, BBContext context, ScreenManager screenManager) {
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
				return TransitionScreen.create(TRANSITION_SCREEN, config, context);
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
						BACKSTORY_SCREEN,
						config,
						context,
						new BackstoryScene());
			}
		});
		screenBuilders.add(new ScreenBuilder() {
			@Override
			public Screen build(){
				return AbortableSceneScreen.create(
						ROSTER_SCREEN,
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
