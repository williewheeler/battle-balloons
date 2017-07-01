package bb.arena;

import bb.BBContext;
import bb.arena.model.ArenaModel;
import bb.arena.view.ArenaFooter;
import bb.arena.view.ArenaHeader;
import bb.arena.view.ArenaPane;
import bb.framework.view.GameScreen;

import java.awt.BorderLayout;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends GameScreen {
	private ArenaModel arenaModel;
	private ArenaHeader arenaHeader;
	private ArenaPane arenaPane;
	private ArenaFooter arenaFooter;

	public ArenaScreen(BBContext context, ArenaModel arenaModel) {
		this.arenaModel = arenaModel;
		this.arenaHeader = new ArenaHeader(context, arenaModel);
		this.arenaPane = new ArenaPane(context, arenaModel);
		this.arenaFooter = new ArenaFooter(context, arenaModel);

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
