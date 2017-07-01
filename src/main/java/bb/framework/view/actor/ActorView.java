package bb.framework.view.actor;

import bb.framework.model.ActorModel;

import java.awt.Graphics;

/**
 * Created by willie on 6/24/17.
 */
public interface ActorView {

	ActorModel getModel();

	void paint(Graphics g);
}
