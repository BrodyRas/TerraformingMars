package TMars.model;

import java.io.Serializable;
import java.util.HashMap;

import TMars.providers.TagProvider;

public class Tableau implements Serializable {

    private Corporation corp;
    private int terraformerRating;
    private HashMap<TagProvider.ResourceTag, Integer> resources;
    private HashMap<TagProvider.ResourceTag, Integer> production;
    private HashMap<TagProvider.CardTag, Integer> myTags;
    private HashMap<TagProvider.CardTag, Integer> theirTags;

    public Tableau(Corporation corp) {
        this.corp = corp;
        terraformerRating = 20; // because that's the default start

        resources = new HashMap<>();
        production = new HashMap<>();
        myTags = new HashMap<>();
        theirTags = new HashMap<>();

        for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
            resources.put(tag, 0);
            production.put(tag, 0);
        }
    }

    // UTILITY
    // Increase resources at the end of the generation, based on your production of that resource
    public void produceResources() {
        // TODO: should we convert Energy to Heat before or after production?
        // TODO: before
        /*resources.put(TagProvider.ResourceTag.Heat,
                resources.get(TagProvider.ResourceTag.Heat)
                        + resources.get(TagProvider.ResourceTag.Energy));*/
        updateResource(TagProvider.ResourceTag.Heat, resources.get(TagProvider.ResourceTag.Energy));
        resources.put(TagProvider.ResourceTag.Energy, 0);

        for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
            updateResource(tag, production.get(tag));
        }
    }

    // For all update functions, "change" can be positive or negative
    public void updateResource(TagProvider.ResourceTag tag, int change) {
        resources.put(tag, resources.get(tag) + change);
    }

    public void updateProduction(TagProvider.ResourceTag tag, int change) {
        production.put(tag, production.get(tag) + change);
    }

    public void updateMyTags(TagProvider.CardTag tag, int change) {
        myTags.put(tag, myTags.get(tag) + change);
    }

    public void updateTheirTags(TagProvider.CardTag tag, int change) {
        theirTags.put(tag, theirTags.get(tag) + change);
    }

    // GETTERS / SETTERS
    public Corporation getCorp() {
        return corp;
    }

    public void setCorp(Corporation corp) {
        this.corp = corp;
    }

    public int getTerraformerRating() {
        return terraformerRating;
    }

    public void setTerraformerRating(int terraformerRating) {
        this.terraformerRating = terraformerRating;
    }

    public int getResource(TagProvider.ResourceTag tag) {
        return resources.get(tag);
    }

    public int getProduction(TagProvider.ResourceTag tag) {
        return production.get(tag);
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

    public HashMap<TagProvider.CardTag, Integer> getMyTags() {
        return myTags;
    }

    public void setMyTags(HashMap<TagProvider.CardTag, Integer> myTags) {
        this.myTags = myTags;
    }

    public HashMap<TagProvider.CardTag, Integer> getTheirTags() {
        return theirTags;
    }

    public void setTheirTags(HashMap<TagProvider.CardTag, Integer> theirTags) {
        this.theirTags = theirTags;
    }
}
