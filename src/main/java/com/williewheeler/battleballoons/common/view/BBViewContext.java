package com.williewheeler.battleballoons.common.view;

import com.williewheeler.battleballoons.common.world.entity.view.ActorViewFactory;
import com.williewheeler.battleballoons.common.view.resource.AudioFactory;
import com.williewheeler.battleballoons.common.view.resource.FontFactory;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import io.halfling.view.BasicViewContext;

// TODO Hm, this is just UI-related stuff. Do we want to keep this isolated from model-related stuff, like levels?
// [WLW]

/**
 * Created by willie on 6/30/17.
 */
public class BBViewContext extends BasicViewContext {
	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private AudioFactory audioFactory;
	private ActorViewFactory actorViewFactory;

	public BBViewContext() {
		this.fontFactory = new FontFactory(getFontLoader());
		this.spriteFactory = new SpriteFactory(getImageLoader());
		this.audioFactory = new AudioFactory(getAudioLoader());
		this.actorViewFactory = new ActorViewFactory(fontFactory, spriteFactory);
	}

	public AudioFactory getAudioFactory() {
		return audioFactory;
	}

	public FontFactory getFontFactory() {
		return fontFactory;
	}

	public SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}

	public ActorViewFactory getActorViewFactory() {
		return actorViewFactory;
	}
}
