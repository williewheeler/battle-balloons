package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.actor.model.Balloon;
import com.williewheeler.bb.common.actor.model.BigBalloon;
import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Dog;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.bb.common.actor.model.Text;
import com.williewheeler.bb.common.resource.FontFactory;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.ActorView;
import com.williewheeler.retroge.util.Assert;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private BalloonView balloonView;
	private BigBalloonView bigBalloonView;
	private BullyView bullyView;
	private DogView dogView;
	private JudoView judoView;
	private LexiView lexiView;
	private ObstacleView obstacleView;
	private TextView textView;

	public ActorViewFactory(FontFactory fontFactory, SpriteFactory spriteFactory) {
		Assert.notNull(fontFactory, "fontFactory can't be null");
		Assert.notNull(spriteFactory, "spriteFactory can't be null");

		this.balloonView = new BalloonView();
		this.bigBalloonView = new BigBalloonView(spriteFactory.getBigBalloons());
		this.bullyView = new BullyView(spriteFactory);
		this.dogView = new DogView(spriteFactory);
		this.judoView = new JudoView(spriteFactory);
		this.lexiView = new LexiView(spriteFactory);
		this.obstacleView = new ObstacleView();
		this.textView = new TextView(fontFactory);
	}

	public ActorView getView(Actor actor) {
		// TODO Refactor to avoid if/else chain. [WLW]
		if (actor instanceof Balloon) {
			return balloonView;
		} else if (actor instanceof BigBalloon) {
			return bigBalloonView;
		} else if (actor instanceof Bully) {
			return bullyView;
		} else if (actor instanceof Dog) {
			return dogView;
		} else if (actor instanceof Judo) {
			return judoView;
		} else if (actor instanceof Lexi) {
			return lexiView;
		} else if (actor instanceof Obstacle) {
			return obstacleView;
		} else if (actor instanceof Text) {
			return textView;
		} else {
			throw new IllegalArgumentException("Illegal actor: " + actor);
		}
	}
}
