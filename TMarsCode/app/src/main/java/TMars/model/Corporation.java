package TMars.model;

public class Corporation extends Card {
    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean activate() {
        return false;
    }

    public int getStartingCredits()
    {
        return 0;
    }
}
