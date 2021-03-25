package TMars.model;

import java.util.HashMap;

import TMars.providers.TagProvider;

public class Tableau {

    private Corporation corp;
    private int terraformerRating;
    private HashMap<TagProvider.ResourceTag, Integer> resources;
    private HashMap<TagProvider.ResourceTag, Integer> production;

    public Tableau(Corporation corp)
    {
        this.corp = corp;
        terraformerRating = 20;

        resources = new HashMap<>();
        production = new HashMap<>();
        for(TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()){
            resources.put(tag, 0);
            production.put(tag, 0);
        }
    }

    // UTILITY
    // Increase resources at the end of the generation, based on your production of that resource
    public void produceResources(){
        for(TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
            updateResource(tag, production.get(tag));
        }
    }

    // GETTERS / SETTERS
    public Corporation getCorp() { return corp; }
    public void setCorp(Corporation corp) { this.corp = corp; }
    public int getTerraformerRating() { return terraformerRating; }
    public void setTerraformerRating(int terraformerRating) { this.terraformerRating = terraformerRating; }
    public int getResource(TagProvider.ResourceTag tag){
        return resources.get(tag);
    }
    public int getProduction(TagProvider.ResourceTag tag){
        return production.get(tag);
    }
    // For both update functions, "change" can obviously also be negative
    public void updateResource(TagProvider.ResourceTag tag, int change){
        resources.put(tag, resources.get(tag) + change);
    }
    public void updateProduction(TagProvider.ResourceTag tag, int change){
        production.put(tag, production.get(tag) + change);
    }
}
