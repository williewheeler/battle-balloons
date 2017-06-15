package bb.model;

import bb.model.event.EntityState;
import bb.model.event.GameEvents;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/15/17.
 */
public class CollisionDetector {
	private GameModel gameModel;

	public CollisionDetector(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	/**
	 * @return boolean indicating whether the player died
	 */
	public boolean checkCollisions() {
		checkPlayerBalloonCollisions();
		checkJudoObstacleCollisions();

		if (checkPlayerObstacleCollision()) {
			return true;
		}
		if (checkPlayerJudoCollision()) {
			return true;
		}

		return false;
	}

	private void checkPlayerBalloonCollisions() {
		List<Balloon> playerBalloons = gameModel.getPlayerBalloons();
		List<Obstacle> obstacles = gameModel.getObstacles();
		List<Judo> judos = gameModel.getJudos();
		Player player = gameModel.getPlayer();

		checkBalloons:
		for (ListIterator<Balloon> bit = playerBalloons.listIterator(); bit.hasNext();) {
			Balloon balloon = bit.next();
			for (ListIterator<Obstacle> oit = obstacles.listIterator(); oit.hasNext();) {
				Obstacle obstacle = oit.next();
				if (balloon.collision(obstacle)) {
					player.incrementScore(Obstacle.SCORE);
					bit.remove();
					oit.remove();
					continue checkBalloons;
				}
			}
			for (ListIterator<Judo> jit = judos.listIterator(); jit.hasNext();) {
				Judo judo = jit.next();
				if (judo.getState() == EntityState.ACTIVE && balloon.collision(judo)) {
					player.incrementScore(Judo.SCORE);
					judo.setState(EntityState.EXITING);
					bit.remove();
					gameModel.fireEvent(GameEvents.JUDO_HIT);
					continue checkBalloons;
				}
			}
		}
	}

	private void checkJudoObstacleCollisions() {
		List<Obstacle> obstacles = gameModel.getObstacles();
		List<Judo> judos = gameModel.getJudos();
		Player player = gameModel.getPlayer();

		checkJudos:
		for (ListIterator<Judo> jit = judos.listIterator(); jit.hasNext();) {
			Judo judo = jit.next();
			for (ListIterator<Obstacle> oit = obstacles.listIterator(); oit.hasNext();) {
				Obstacle obstacle = oit.next();
				if (judo.getState() == EntityState.ACTIVE && judo.collision(obstacle)) {
					player.incrementScore(Judo.SCORE);
					player.incrementScore(Obstacle.SCORE);
					judo.setState(EntityState.EXITING);
					oit.remove();
					gameModel.fireEvent(GameEvents.JUDO_HIT);
					continue checkJudos;
				}
			}
		}
	}

	private boolean checkPlayerObstacleCollision() {
		List<Obstacle> obstacles = gameModel.getObstacles();
		Player player = gameModel.getPlayer();

		if (player.getState() == EntityState.ACTIVE) {
			for (ListIterator<Obstacle> it = obstacles.listIterator(); it.hasNext(); ) {
				Obstacle obstacle = it.next();
				if (player.collision(obstacle)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkPlayerJudoCollision() {
		List<Judo> judos = gameModel.getJudos();
		Player player = gameModel.getPlayer();

		if (player.getState() == EntityState.ACTIVE) {
			for (ListIterator<Judo> it = judos.listIterator(); it.hasNext(); ) {
				Judo judo = it.next();
				if (judo.getState() == EntityState.ACTIVE && player.collision(judo)) {
					return true;
				}
			}
		}
		return false;
	}

}
