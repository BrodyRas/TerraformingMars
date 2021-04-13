package TMars.providers;

import edu.byu.cs.tweeter.R;

public class TagProvider {
    public enum CardTag {
        Building,
        Space,
        Plant,
        Microbe,
        Animal,
        Power,
        Science,
        Jovian,
        Earth,
        City,
        Event,
        Wild
    }

    public enum ResourceTag {
        Rating,
        Credits,
        Iron,
        Titanium,
        Plant,
        Energy,
        Heat
    }

    public static int TagToImage(CardTag tag)
    {
        switch (tag)
        {
            case Building:
                return R.drawable.tag_building;
            case Space:
                return R.drawable.tag_space;
            case Plant:
                return R.drawable.tag_plant;
            case Microbe:
                return R.drawable.tag_microbe;
            case Animal:
                return R.drawable.tag_animal;
            case Power:
                return R.drawable.tag_power;
            case Science:
                return R.drawable.tag_science;
            case Jovian:
                return R.drawable.tag_jovian;
            case Earth:
                return R.drawable.tag_earth;
            case City:
                return R.drawable.tag_city;
            case Event:
                return R.drawable.tag_event;
            case Wild:
                return R.drawable.tag_wild;
            default:
                return -1;
        }
    }

    public static String ResToString(ResourceTag tag)
    {
        switch (tag)
        {
            case Rating:
                return "TR";
            case Credits:
                return "Credits";
            case Iron:
                return "Iron";
            case Titanium:
                return "Titanium";
            case Plant:
                return "Plant";
            case Energy:
                return "Energy";
            case Heat:
                return "Heat";
            default:
                return "NULL";
        }
    }
}
