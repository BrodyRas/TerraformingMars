package TMars.model;

public class Corporation extends Card {

    public Corporation(int corpID)
    {
        //use ID to determine which corporation to generate
        setName("Corportation " + corpID);
    }

    public Corporation()
    {

    }

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
