package TMars.model;

import edu.byu.cs.tweeter.R;

public class Row {

    public int rownum;
    public int iconID;
    public int colorID;

    public Row(int id)
    {
        rownum = id;
        switch(id)
        {
            case 0:
                iconID = R.drawable.res_tr;
                colorID = R.color.tr;
                break;
            case 1:
                iconID = R.drawable.res_megacredit;
                colorID = R.color.gold;
                break;
            case 2:
                iconID = R.drawable.res_steel;
                colorID = R.color.steel;
                break;
            case 3:
                iconID = R.drawable.res_titanium;
                colorID = R.color.titan;
                break;
            case 4:
                iconID = R.drawable.res_plant;
                colorID = R.color.plant;
                break;
            case 5:
                iconID = R.drawable.res_power;
                colorID = R.color.power;
                break;
            case 6:
                iconID = R.drawable.res_heat;
                colorID = R.color.heat;
                break;
        }
    }
}
