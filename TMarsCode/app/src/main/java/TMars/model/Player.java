package TMars.model;

/**
 * Container object, holds anything that needs to be saved between sessions
 */
public class Player {
    public Tableau mTableau;
    //public Hand mHand;
    //boolean settings //We'll have boolean flags for the settings

    public Player(Corporation corp)
    {
        mTableau = new Tableau(corp);
    }
}
