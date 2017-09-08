package bb.model;

/**
 * Created by wwheeler on 6/12/17.
 */
public class Judo extends AbstractEntity {

    public Judo(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
