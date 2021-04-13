package TMars.model;

public class Score {

    int baseScore;
    int pointPerResource;
    int resourceCount;

    public int getScore()
    {
        return baseScore + (pointPerResource * resourceCount);
    }

    public Score(int base, int ppr, int rc)
    {
        baseScore = base;
        pointPerResource = ppr;
        resourceCount = rc;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public int getPointPerResource() {
        return pointPerResource;
    }

    public void setPointPerResource(int pointPerResource) {
        this.pointPerResource = pointPerResource;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }
}
