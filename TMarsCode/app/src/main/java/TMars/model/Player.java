package TMars.model;

/**
 * Container object, holds anything that needs to be saved between sessions
 */
public class Player implements java.io.Serializable {
    private static Player instance;
    //tell if a player needs init called or not
    private static boolean instantiated;

    private Player(Corporation corp){ tableau = new Tableau(corp); }

    public static Player getInstance(Corporation corp){
        if(instance == null){
            instance = new Player(corp);
            instantiated = false;
            return instance;
        }
        instantiated = true;
        return instance;
    }

    public Tableau tableau;
    //public Hand mHand;
    //boolean settings //We'll have boolean flags for the setting

    public void init(Corporation corp)
    {
        tableau = new Tableau(corp);
    }


    public Tableau getTableau() {
        return tableau;
    }

    public void setTableau(Tableau tableau) {
        this.tableau = tableau;
    }
}
