package TMars.presenters;

import TMars.model.Player;
import TMars.model.Tableau;
import TMars.providers.TagProvider;

/**
 * Stands between the fragment and the model such that changes to one need not affect the other.
 */
public class TableauPresenter {

    private final Player player;

    public TableauPresenter(Player player)
    {
        this.player = player;
    }

    public String tapQ(TagProvider.ResourceTag rowNumber, int amount)
    {
        player.tableau.updateResource(rowNumber,amount);
        return String.valueOf(player.tableau.getResource(rowNumber));
    }

    public String tapP(TagProvider.ResourceTag rowNumber, int amount)
    {
        player.tableau.updateProduction(rowNumber,amount);
        return String.valueOf(player.tableau.getProduction(rowNumber));
    }

    public String tapMe(TagProvider.CardTag rowNumber, int amount)
    {
        player.tableau.updateMyTags(rowNumber,amount);
        return String.valueOf(player.tableau.getMyTag(rowNumber));
    }

    public String tapO(TagProvider.CardTag rowNumber, int amount)
    {
        player.tableau.updateTheirTags(rowNumber,amount);
        return String.valueOf(player.tableau.getOtherTag(rowNumber));
    }
}
