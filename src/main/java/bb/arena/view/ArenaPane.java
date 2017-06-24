package bb.arena.view;

import bb.arena.model.Balloon;
import bb.arena.model.ArenaModel;
import bb.arena.model.Judo;
import bb.arena.model.Obstacle;
import bb.arena.model.Player;
import bb.common.view.SpriteFactory;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaPane extends JComponent {
	private ArenaModel arenaModel;
	private SpriteFactory spriteFactory;

	public ArenaPane(ArenaModel arenaModel, SpriteFactory spriteFactory) {
		this.arenaModel = arenaModel;
		this.spriteFactory = spriteFactory;
	}

	@Override
	public Dimension getPreferredSize() {
		return ARENA_PANE_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		g.translate(ARENA_MARGIN_LEFT_RIGHT_PX, 0);
		doPaintBorder(g);
		g.translate(ARENA_BORDER_SIZE_PX, ARENA_BORDER_SIZE_PX);
		g.setClip(0, 0, ARENA_INNER_WIDTH_PX, ARENA_INNER_HEIGHT_PX);
		paintObstacles(g);
		paintJudos(g);
		paintPlayer(g);
		paintPlayerBalloons(g);
		g.translate(-ARENA_BORDER_SIZE_PX, -ARENA_BORDER_SIZE_PX);
		g.translate(-ARENA_MARGIN_LEFT_RIGHT_PX, 0);
	}

	private void doPaintBorder(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, ARENA_OUTER_WIDTH_PX, ARENA_OUTER_HEIGHT_PX);
		g.setColor(Color.BLACK);
		g.fillRect(ARENA_BORDER_SIZE_PX, ARENA_BORDER_SIZE_PX, ARENA_INNER_WIDTH_PX, ARENA_INNER_HEIGHT_PX);
	}

	private void paintPlayer(Graphics g) {
		switch (arenaModel.getPlayer().getState()) {
			case ENTERING:
				paintPlayer_entering(g);
				break;
			case ACTIVE:
				paintPlayer_active(g);
				break;
			case EXITING:
				paintPlayer_exiting(g);
				break;
			case GONE:
				break;
		}
	}
	
	private void paintPlayer_entering(Graphics g) {
		Player player = arenaModel.getPlayer();
		
		BufferedImage[] sprites = spriteFactory.getLexiEntering();
		int spriteIndex = player.getEnteringCountdown();
		BufferedImage sprite = sprites[spriteIndex];
		
		int adjX = player.getX() - sprite.getWidth() / 2;
		int adjY = player.getY() - sprite.getHeight() / 2;
		g.drawImage(sprite, adjX, adjY, sprite.getWidth(), sprite.getHeight(), null);
	}
	
	private void paintPlayer_active(Graphics g) {
		Player player = arenaModel.getPlayer();
		BufferedImage[] sprites = spriteFactory.getLexi();
		BufferedImage sprite = SpriteUtil.getCurrentSprite(player, sprites);
		int adjX = player.getX() - sprite.getWidth() / 2;
		int adjY = player.getY() - sprite.getHeight() / 2;
		g.drawImage(sprite, adjX, adjY, sprite.getWidth(), sprite.getHeight(), null);
	}
	
	private void paintPlayer_exiting(Graphics g) {
		Player player = arenaModel.getPlayer();
		BufferedImage[] sprites = spriteFactory.getLexiExiting();
		
		int spriteIndex = (Player.EXITING_DURATION - 1) - player.getExitingCountdown();
		BufferedImage sprite = sprites[spriteIndex];
		
		int adjX = player.getX() - sprite.getWidth() / 2;
		int adjY = player.getY() - sprite.getHeight() / 2;
		g.drawImage(sprite, adjX, adjY, sprite.getWidth(), sprite.getHeight(), null);
	}
	
	private void paintObstacles(Graphics g) {
		g.setColor(Color.GREEN);
		List<Obstacle> obstacles = arenaModel.getObstacles();
		obstacles.forEach(obstacle -> paintObstacle(g, obstacle));
	}
	
	private void paintObstacle(Graphics g, Obstacle obstacle) {
		int adjX = obstacle.getX() - obstacle.getWidth() / 2;
		int adjY = obstacle.getY() - obstacle.getHeight() / 2;
		g.fillRect(adjX, adjY, obstacle.getWidth(), obstacle.getHeight());
	}
	
	private void paintJudos(Graphics g) {
		List<Judo> judos = arenaModel.getJudos();
		judos.forEach(judo -> paintJudo(g, judo));
	}
	
	private void paintJudo(Graphics g, Judo judo) {
		switch (judo.getState()) {
			case ENTERING:
				paintJudo_entering(g, judo);
				break;
			case ACTIVE:
				paintJudo_active(g, judo);
				break;
			case EXITING:
				paintJudo_exiting(g, judo);
				break;
			case GONE:
				break;
		}
	}

	private void paintJudo_entering(Graphics g, Judo judo) {
		BufferedImage[] sprites = spriteFactory.getJudoEntering();
		int spriteIndex = judo.getEnteringCountdown();
		BufferedImage sprite = sprites[spriteIndex];

		int adjX = judo.getX() - sprite.getWidth() / 2;
		int adjY = judo.getY() - sprite.getHeight() / 2;
		g.drawImage(sprite, adjX, adjY, sprite.getWidth(), sprite.getHeight(), null);
	}

	private void paintJudo_active(Graphics g, Judo judo) {
		BufferedImage[] sprites = spriteFactory.getJudo();
		BufferedImage sprite = SpriteUtil.getCurrentSprite(judo, sprites);
		int adjX = judo.getX() - SPRITE_WIDTH_PX / 2;
		int adjY = judo.getY() - SPRITE_HEIGHT_PX / 2;
		g.drawImage(sprite, adjX, adjY, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
	}

	private void paintJudo_exiting(Graphics g, Judo judo) {
		BufferedImage[] sprites = spriteFactory.getJudoExiting();

		int spriteIndex = (Judo.EXITING_DURATION - 1) - judo.getExitingCountdown();
		BufferedImage sprite = sprites[spriteIndex];

		int adjX = judo.getX() - sprite.getWidth() / 2;
		int adjY = judo.getY() - sprite.getHeight() / 2;
		g.drawImage(sprite, adjX, adjY, sprite.getWidth(), sprite.getHeight(), null);
	}

	private void paintPlayerBalloons(Graphics g) {
		g.setColor(Color.CYAN);
		List<Balloon> balloons = arenaModel.getPlayerBalloons();
		balloons.forEach(balloon -> paintPlayerBalloon(g, balloon));
	}
	
	private void paintPlayerBalloon(Graphics g, Balloon balloon) {
		int adjX = balloon.getX() - balloon.getWidth() / 2;
		int adjY = balloon.getY() - balloon.getHeight() / 2;
		g.fillRect(adjX, adjY, balloon.getWidth(), balloon.getHeight());
	}
}
