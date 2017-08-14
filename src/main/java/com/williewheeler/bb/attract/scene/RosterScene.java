package com.williewheeler.bb.attract.scene;

import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.bb.common.actor.model.Text;
import com.williewheeler.bb.common.actor.model.YardDuty;
import com.williewheeler.retroge.actor.brain.BasicActorBrain;

/**
 * Created by willie on 7/2/17.
 */
public class RosterScene extends ScriptScene {
	private Text lexiText;
	private Lexi lexi;
	private Obstacle obstacle;
	private Judo judo;
	private YardDuty yardDuty;
	private Bully bully;

	public RosterScene() {
		initActors();
	}

	@Override
	public void doScript(int counter) {

		// TODO Move this script (or something like it) into the backstory scene.
		// TODO Possible refactoring target. [WLW]
		if (counter == 0) {
			getTexts().add(lexiText);
			getLexis().add(lexi);
			getObstacles().add(obstacle);
		} else if (counter == 60) {
			lexi.setSubstate(Lexi.Substate.WAVING);
		} else if (counter == 90) {
			lexi.setSubstate(Lexi.Substate.BATTLING);
			lexi.getBrain().getMoveDirectionIntent().right = true;
		} else if (counter == 140) {
			lexi.getBrain().getFireDirectionIntent().right = true;
		} else if (counter == 141) {
			lexi.getBrain().getFireDirectionIntent().reset();
		} else if (counter == 145) {
			lexi.setSubstate(Lexi.Substate.BLINKING);
			lexi.getBrain().getMoveDirectionIntent().right = false;
		} else if (counter == 160) {
			getJudos().add(judo);
		} else if (counter == 170) {
			lexi.setSubstate(Lexi.Substate.BATTLING);
			lexi.getBrain().getMoveDirectionIntent().left = true;
			lexi.getBrain().getFireDirectionIntent().left = true;
		} else if (counter == 171) {
			lexi.getBrain().getMoveDirectionIntent().reset();
			lexi.getBrain().getFireDirectionIntent().reset();
		} else if (counter == 200) {
			lexi.setSubstate(Lexi.Substate.WAVING);
		} else if (counter == 240) {
			getYardDuties().add(yardDuty);
		} else if (counter == 300) {
			lexi.setSubstate(Lexi.Substate.BATTLING);
			lexi.getBrain().getMoveDirectionIntent().left = true;
			lexi.getBrain().getFireDirectionIntent().left = true;
		} else if (counter == 340) {
			lexi.getBrain().getMoveDirectionIntent().reset();
			lexi.getBrain().getFireDirectionIntent().reset();
		} else if (counter == 400) {
			getBullies().add(bully);
		} else if (counter == 450) {
			lexi.setSubstate(Lexi.Substate.BATTLING);
			lexi.getBrain().getMoveDirectionIntent().right = true;
			lexi.getBrain().getFireDirectionIntent().right = true;
		} else if (counter == 500) {
			lexi.getBrain().getMoveDirectionIntent().reset();
			lexi.getBrain().getFireDirectionIntent().reset();
		} else if (counter > 600) {
			setActive(false);
		}
	}

	private void initActors() {
		this.lexiText = new Text("Hello, I'm Lexi.", 40, 140);
		lexiText.setScene(this);

		this.lexi = new Lexi(new BasicActorBrain(), 50, 180);
		lexi.setScene(this);

		this.obstacle = new Obstacle(this, 240, 180);
		this.judo = new Judo(this, new BasicActorBrain(), 50, 180);
		this.yardDuty = new YardDuty(this, new BasicActorBrain(), 50, 180);
		this.bully = new Bully(this, new BasicActorBrain(), 250, 180);
	}
}
