package bb.view.arena;

import bb.model.GameModel;
import bb.view.FontFactory;
import bb.view.SpriteFactory;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaView extends JPanel {
	private ArenaHeader arenaHeader;
	private ArenaPane arenaPane;
	private ArenaFooter arenaFooter;

	public ArenaView(GameModel gameModel, FontFactory fontFactory, SpriteFactory spriteFactory) {
		this.arenaHeader = new ArenaHeader(gameModel, fontFactory, spriteFactory);
		this.arenaPane = new ArenaPane(gameModel, spriteFactory);
		this.arenaFooter = new ArenaFooter(gameModel, fontFactory);

		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		add(arenaHeader, BorderLayout.NORTH);
		add(arenaPane, BorderLayout.CENTER);
		add(arenaFooter, BorderLayout.SOUTH);
	}
}
