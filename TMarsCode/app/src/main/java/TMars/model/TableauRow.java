package TMars.model;

import TMars.providers.TagProvider;
import edu.byu.cs.tweeter.R;

public class TableauRow {

    public int rownum;
    public int iconID;
    public int colorID;
    public TagProvider.ResourceTag tag;

    public TableauRow(int id)
    {
        rownum = id;
        switch(id)
        {
            case 0:
                iconID = R.drawable.res_tr;
                colorID = R.color.tr;
                tag = TagProvider.ResourceTag.Rating;
                break;
            case 1:
                iconID = R.drawable.res_megacredit;
                colorID = R.color.gold;
                tag = TagProvider.ResourceTag.Credits;
                break;
            case 2:
                iconID = R.drawable.res_steel;
                colorID = R.color.steel;
                tag = TagProvider.ResourceTag.Iron;
                break;
            case 3:
                iconID = R.drawable.res_titanium;
                colorID = R.color.titan;
                tag = TagProvider.ResourceTag.Titanium;
                break;
            case 4:
                iconID = R.drawable.res_plant;
                colorID = R.color.plant;
                tag = TagProvider.ResourceTag.Plant;
                break;
            case 5:
                iconID = R.drawable.res_power;
                colorID = R.color.power;
                tag = TagProvider.ResourceTag.Energy;
                break;
            case 6:
                iconID = R.drawable.res_heat;
                colorID = R.color.heat;
                tag = TagProvider.ResourceTag.Heat;
                break;
        }
    }
}
