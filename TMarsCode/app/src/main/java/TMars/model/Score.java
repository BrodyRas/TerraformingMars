package TMars.model;

public class Score {

    int baseScore;
    int pointPerResource;
    int resourceCount;

    public int getScore()
    {
        return baseScore + (pointPerResource * resourceCount);
    }
}
