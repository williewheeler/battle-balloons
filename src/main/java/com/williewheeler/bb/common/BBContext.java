package com.williewheeler.bb.common;

import com.williewheeler.bb.common.actor.view.ActorViewFactory;
import com.williewheeler.bb.common.resource.AudioFactory;
import com.williewheeler.bb.common.resource.FontFactory;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.BasicGameContext;

// TODO Hm, this is just UI-related stuff. Do we want to keep this isolated from model-related stuff, like levels?
// [WLW]

/**
 * Created by willie on 6/30/17.
 */
public class BBContext extends BasicGameContext {
	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private AudioFactory audioFactory;
	private ActorViewFactory actorViewFactory;

	public BBContext() {
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
