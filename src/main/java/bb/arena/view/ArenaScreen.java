package bb.arena.view;

import bb.arena.model.ArenaModel;
import bb.common.view.factory.FontFactory;
import bb.framework.view.GameScreen;
import bb.common.view.factory.SpriteFactory;

import java.awt.BorderLayout;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends GameScreen {
	private ArenaModel arenaModel;
	private ArenaHeader arenaHeader;
	private ArenaPane arenaPane;
	private ArenaFooter arenaFooter;

	public ArenaScreen(ArenaModel arenaModel, FontFactory fontFactory, SpriteFactory spriteFactory) {
		this.arenaModel = arenaModel;
		this.arenaHeader = new ArenaHeader(arenaModel, fontFactory, spriteFactory);
		this.arenaPane = new ArenaPane(arenaModel, spriteFactory);
		this.arenaFooter = new ArenaFooter(arenaModel, fontFactory);

		setLayout(new BorderLayout());
		add(arenaHeader, BorderLayout.NORTH);
		add(arenaPane, BorderLayout.CENTER);
		add(arenaFooter, BorderLayout.SOUTH);
	}

	// TODO Get rid of this once ArenaModel is gone.
	@Deprecated
	@Override
	public void update() {
		super.update();
		arenaModel.update();
	}
}
