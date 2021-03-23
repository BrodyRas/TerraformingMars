package TMars.model;

import java.util.ArrayList;


/**
 * Describes the basics of a card, extended by GreenCard, BlueCard, RedCard, Prelude, Corporation
 */
public abstract class Card {
    String id;
    int cost;
    ArrayList<Integer> tags;
    Requirement requires;
    Score points;
    ArrayList<Integer> adjustProduction;
    ArrayList<Integer> adjustResources;

    public Card(){};

    public abstract boolean play();

    public abstract boolean activate();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public Requirement getRequires() {
        return requires;
    }

    public void setRequires(Requirement requires) {
        this.requires = requires;
    }

    public Score getPoints() {
        return points;
    }

    public void setPoints(Score points) {
        this.points = points;
    }

    public ArrayList<Integer> getAdjustProduction() {
        return adjustProduction;
    }

    public void setAdjustProduction(ArrayList<Integer> adjustProduction) {
        this.adjustProduction = adjustProduction;
    }

    public ArrayList<Integer> getAdjustResources() {
        return adjustResources;
    }

    public void setAdjustResources(ArrayList<Integer> adjustResources) {
        this.adjustResources = adjustResources;
    }
}
