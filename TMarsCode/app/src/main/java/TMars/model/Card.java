package TMars.model;

import java.util.ArrayList;
import java.util.HashMap;

import TMars.providers.TagProvider;


/**
 * Describes the basics of a card, extended by GreenCard, BlueCard, RedCard, Prelude, Corporation
 */
public abstract class Card {
    private String id;
    private String name;
    private int cost;
    private Requirement requires;
    private Score points;
    private HashMap<TagProvider.ResourceTag, Integer> resources;
    private HashMap<TagProvider.ResourceTag, Integer> production;
    private HashMap<TagProvider.CardTag, Integer> tags;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<TagProvider.ResourceTag, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<TagProvider.ResourceTag, Integer> resources) {
        this.resources = resources;
    }

    public HashMap<TagProvider.ResourceTag, Integer> getProduction() {
        return production;
    }

    public void setProduction(HashMap<TagProvider.ResourceTag, Integer> production) {
        this.production = production;
    }

    public HashMap<TagProvider.CardTag, Integer> getTags() {
        return tags;
    }

    public void setTags(HashMap<TagProvider.CardTag, Integer> tags) {
        this.tags = tags;
    }
}
