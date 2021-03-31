package TMars.model;

/**
 * Container object, holds anything that needs to be saved between sessions
 */
public class Player implements java.io.Serializable {
    private static Player instance;

    private Player(){ }

    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
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
