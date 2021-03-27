package TMars.model;

/**
 * Container object, holds anything that needs to be saved between sessions
 */
public class Player implements java.io.Serializable {
    public Tableau tableau;
    //public Hand mHand;
    //boolean settings //We'll have boolean flags for the settings

    public Player(Corporation corp)
    {
        tableau = new Tableau(corp);
    }

    public Player(){}

    public Tableau getTableau() {
        return tableau;
    }

    public void setTableau(Tableau tableau) {
        this.tableau = tableau;
    }
}
