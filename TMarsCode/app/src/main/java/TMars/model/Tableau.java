package TMars.model;

import java.io.Serializable;
import java.util.HashMap;

import TMars.providers.TagProvider.CardTag;
import TMars.providers.TagProvider.ResourceTag;

public class Tableau implements Serializable {

    private Corporation corp;
    private HashMap<ResourceTag, Integer> resources;
    private HashMap<ResourceTag, Integer> productions;
    private HashMap<CardTag, Integer> myTags;
    private HashMap<CardTag, Integer> theirTags;

    public Tableau(Corporation corp) {
        this.corp = corp;
        //we made rating a resource so it doesn't need it's own member
        //terraformerRating = 20; // because that's the default start

        /**resources = new HashMap<>();
        production = new HashMap<>();
        myTags = new HashMap<>();*/
        resources = corp.getResources();
        productions = corp.getProduction();
        myTags = corp.getTags();

        theirTags = new HashMap<>();

        for (ResourceTag tag : ResourceTag.values()) {
            if (resources.get(tag) == null) resources.put(tag, 0);
            if (productions.get(tag) == null) productions.put(tag, 0);
        }

        resources.put(ResourceTag.Rating, 20);

        for (CardTag tag : CardTag.values()) {
            if (myTags.get(tag) == null) myTags.put(tag, 0);
            theirTags.put(tag, 0);
        }

        // Then add the corporation values

    }

    // UTILITY
    // Increase resources at the end of the generation, based on your production of that resource
    public void produceResources() {
        // TODO: should we convert Energy to Heat before or after production?
        /**resources.put(ResourceTag.Heat,
                resources.get(ResourceTag.Heat)
                        + resources.get(ResourceTag.Energy));*/
        updateResource(ResourceTag.Heat, resources.get(ResourceTag.Energy));

        resources.put(ResourceTag.Energy, 0);

        updateResource(ResourceTag.Credits, resources.get(ResourceTag.Rating));

        for (ResourceTag tag : ResourceTag.values()) {
            updateResource(tag, productions.get(tag));
        }

    }

    // For all update functions, "change" can be positive or negative
    public void updateResource(ResourceTag tag, int change) {
        resources.put(tag, resources.get(tag) + change);
        if(resources.get(tag) < 0){
            resources.put(tag, 0);
        }
    }

    public void updateProduction(ResourceTag tag, int change) {
        productions.put(tag, productions.get(tag) + change);
        if(productions.get(tag) < 0 && tag != ResourceTag.Credits){
            productions.put(tag, 0);
        }
    }

    public void updateMyTags(CardTag tag, int change) {
        myTags.put(tag, myTags.get(tag) + change);
    }

    public void updateTheirTags(CardTag tag, int change) {
        theirTags.put(tag, theirTags.get(tag) + change);
    }

    // GETTERS / SETTERS
    public Corporation getCorp() {
        return corp;
    }

    public void setCorp(Corporation corp) {
        this.corp = corp;
    }

    public int getResource(ResourceTag tag) {
        return resources.get(tag);
    }

    public int setResource(ResourceTag tag, int amount) { return resources.put(tag, amount); }

    public int getProduction(ResourceTag tag) {
        return productions.get(tag);
    }

    public int setProduction(ResourceTag tag, int amount) {
        return productions.put(tag, amount);
    }

    public HashMap<ResourceTag, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<ResourceTag, Integer> resources) {
        this.resources = resources;
    }

    public HashMap<ResourceTag, Integer> getProductions() {
        return productions;
    }

    public void setProductions(HashMap<ResourceTag, Integer> production) {
        this.productions = production;
    }

    public int getMyTag(CardTag tag) {
        return myTags.get(tag);
    }

    public int getOtherTag(CardTag tag) {
        return theirTags.get(tag);
    }

    public HashMap<CardTag, Integer> getMyTags() {
        return myTags;
    }

    public void setMyTags(HashMap<CardTag, Integer> myTags) {
        this.myTags = myTags;
    }

    public HashMap<CardTag, Integer> getTheirTags() {
        return theirTags;
    }

    public void setTheirTags(HashMap<CardTag, Integer> theirTags) {
        this.theirTags = theirTags;
    }
}
