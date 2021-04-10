package TMars.model;

import TMars.providers.TagProvider;
import edu.byu.cs.tweeter.R;

public class TagRow {

    public int rownum;
    public int iconID;
    public TagProvider.CardTag tag;

    public TagRow(int id)
    {
        rownum = id;
        switch(id)
        {
            case 0:
                iconID = R.drawable.tag_building;
                tag = TagProvider.CardTag.Building;
                break;
            case 1:
                iconID = R.drawable.tag_space;
                tag = TagProvider.CardTag.Space;
                break;
            case 2:
                iconID = R.drawable.tag_plant;
                tag = TagProvider.CardTag.Plant;
                break;
            case 3:
                iconID = R.drawable.tag_microbe;
                tag = TagProvider.CardTag.Microbe;
                break;
            case 4:
                iconID = R.drawable.tag_animal;
                tag = TagProvider.CardTag.Animal;
                break;
            case 5:
                iconID = R.drawable.tag_power;
                tag = TagProvider.CardTag.Power;
                break;
            case 6:
                iconID = R.drawable.tag_science;
                tag = TagProvider.CardTag.Science;
                break;
            case 7:
                iconID = R.drawable.tag_jovian;
                tag = TagProvider.CardTag.Jovian;
                break;
            case 8:
                iconID = R.drawable.tag_earth;
                tag = TagProvider.CardTag.Earth;
                break;
            case 9:
                iconID = R.drawable.tag_city;
                tag = TagProvider.CardTag.City;
                break;
            case 10:
                iconID = R.drawable.tag_event;
                tag = TagProvider.CardTag.Event;
                break;
            case 11:
                iconID = R.drawable.tag_wild;
                tag = TagProvider.CardTag.Wild;
                break;
            default:
                iconID = -1;
                tag = null;

        }
    }
}
