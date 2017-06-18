package bb.arena.view;

import bb.arena.model.ArenaModel;
import bb.core.view.FontFactory;
import bb.core.view.SpriteFactory;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends JPanel {
	private ArenaHeader arenaHeader;
	private ArenaPane arenaPane;
	private ArenaFooter arenaFooter;

	public ArenaScreen(ArenaModel arenaModel, FontFactory fontFactory, SpriteFactory spriteFactory) {
		this.arenaHeader = new ArenaHeader(arenaModel, fontFactory, spriteFactory);
		this.arenaPane = new ArenaPane(arenaModel, spriteFactory);
		this.arenaFooter = new ArenaFooter(arenaModel, fontFactory);

		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		add(arenaHeader, BorderLayout.NORTH);
		add(arenaPane, BorderLayout.CENTER);
		add(arenaFooter, BorderLayout.SOUTH);
	}
}
