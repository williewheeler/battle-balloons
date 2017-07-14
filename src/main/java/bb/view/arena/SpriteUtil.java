package bb.view.arena;

import bb.model.Direction;
import bb.model.Player;

import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/9/17.
 */
public class SpriteUtil {

    public static BufferedImage getCurrentSprite(Player player, BufferedImage[] sprites) {
        Direction direction = player.getDirection();

        // spriteBaseIndex 0 = Up
        // spriteBaseIndex 4 = Down
        // spriteBaseIndex 8 = Left
        // spriteBaseIndex 12 = Right
        int spriteBaseIndex = 0;
        switch (direction) {
            case LEFT:
            case UP_LEFT:
            case DOWN_LEFT:
                spriteBaseIndex = 8;
                break;
            // TODO: Add remaining direction cases
        }

        int spriteIndex = spriteBaseIndex + player.getAnimationCounter();
        return sprites[spriteIndex];
    }
}
