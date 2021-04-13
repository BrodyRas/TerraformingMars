package TMars.model;

public class GreenCard extends Card {
    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean activate() {
        return false;
    }
}
