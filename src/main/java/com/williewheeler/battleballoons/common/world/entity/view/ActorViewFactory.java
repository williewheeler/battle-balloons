package com.williewheeler.battleballoons.common.world.entity.view;

import com.williewheeler.battleballoons.common.world.entity.model.Balloon;
import com.williewheeler.battleballoons.common.world.entity.model.Beat;
import com.williewheeler.battleballoons.common.world.entity.model.Bengy;
import com.williewheeler.battleballoons.common.world.entity.model.BigBalloon;
import com.williewheeler.battleballoons.common.world.entity.model.Bully;
import com.williewheeler.battleballoons.common.world.entity.model.Cat;
import com.williewheeler.battleballoons.common.world.entity.model.Dog;
import com.williewheeler.battleballoons.common.world.entity.model.Judo;
import com.williewheeler.battleballoons.common.world.entity.model.Lexi;
import com.williewheeler.battleballoons.common.world.entity.model.Obstacle;
import com.williewheeler.battleballoons.common.world.entity.model.Parrot;
import com.williewheeler.battleballoons.common.world.entity.model.Teacher;
import com.williewheeler.battleballoons.common.world.entity.model.Text;
import com.williewheeler.battleballoons.common.world.entity.model.Turntables;
import com.williewheeler.battleballoons.common.world.entity.model.YardDuty;
import com.williewheeler.battleballoons.common.view.resource.FontFactory;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.view.ActorView;
import io.halfling.core.Assert;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private LexiView lexiView;
	private BalloonView balloonView;
	private ObstacleView obstacleView;
	private JudoView judoView;
	private BullyView bullyView;
	private YardDutyView yardDutyView;
	private TeacherView teacherView;
	private BengyView bengyView;
	private TurntablesView turntablesView;
	private BeatView beatView;
	private CatView catView;
	private DogView dogView;
	private ParrotView parrotView;
	private BigBalloonView bigBalloonView;
	private TextView textView;

	public ActorViewFactory(FontFactory fontFactory, SpriteFactory spriteFactory) {
		Assert.notNull(fontFactory, "fontFactory can't be null");
		Assert.notNull(spriteFactory, "spriteFactory can't be null");

		this.lexiView = new LexiView(spriteFactory);
		this.balloonView = new BalloonView();
		this.obstacleView = new ObstacleView();
		this.judoView = new JudoView(spriteFactory);
		this.bullyView = new BullyView(spriteFactory);
		this.yardDutyView = new YardDutyView(spriteFactory);
		this.teacherView = new TeacherView(spriteFactory);
		this.bengyView = new BengyView(spriteFactory);
		this.turntablesView = new TurntablesView(spriteFactory);
		this.beatView = new BeatView(spriteFactory);
		this.catView = new CatView(spriteFactory);
		this.dogView = new DogView(spriteFactory);
		this.parrotView = new ParrotView(spriteFactory);
		this.bigBalloonView = new BigBalloonView(spriteFactory.getBigBalloons());
		this.textView = new TextView(fontFactory);
	}

	public ActorView getView(Actor actor) {
		// TODO Refactor to avoid if/else chain. [WLW]
		if (actor instanceof Lexi) {
			return lexiView;
		} else if (actor instanceof Balloon) {
			return balloonView;
		} else if (actor instanceof Obstacle) {
			return obstacleView;
		} else if (actor instanceof Judo) {
			return judoView;
		} else if (actor instanceof Bully) {
			return bullyView;
		} else if (actor instanceof YardDuty) {
			return yardDutyView;
		} else if (actor instanceof Teacher) {
			return teacherView;
		} else if (actor instanceof Bengy) {
			return bengyView;
		} else if (actor instanceof Turntables) {
			return turntablesView;
		} else if (actor instanceof Beat) {
			return beatView;
		} else if (actor instanceof Dog) {
			return dogView;
		} else if (actor instanceof Cat) {
			return catView;
		} else if (actor instanceof Parrot) {
			return parrotView;
		} else if (actor instanceof BigBalloon) {
			return bigBalloonView;
		} else if (actor instanceof Text) {
			return textView;
		} else {
			throw new IllegalArgumentException("Illegal actor: " + actor);
		}
	}
}
