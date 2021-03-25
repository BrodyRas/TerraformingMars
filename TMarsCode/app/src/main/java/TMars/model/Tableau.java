package TMars.model;

import java.util.HashMap;

import TMars.providers.TagProvider;

public class Tableau {

    private Corporation corp;
    private int terraformerRating;
    private HashMap<TagProvider.Tags, Integer> resources;
    private HashMap<TagProvider.Tags, Integer> production;

    public Tableau(Corporation corp)
    {
        this.corp = corp;
        terraformerRating = 20;

        for(TagProvider.Tags t : TagProvider.Tags.values()){
            resources.put(t, 0);
            resources.put(t, 0);
        }
    }

    // UTILITY
    public void updateResource(TagProvider.Tags tag, int change){
        resources.put(tag, resources.get(tag) + change);
    }
    public void updateProduction(TagProvider.Tags tag, int change){
        production.put(tag, production.get(tag) + change);
    }

    // GETTERS / SETTERS
    public Corporation getCorp() { return corp; }
    public void setCorp(Corporation corp) { this.corp = corp; }
    public int getTerraformerRating() { return terraformerRating; }
    public void setTerraformerRating(int terraformerRating) { this.terraformerRating = terraformerRating; }
}
