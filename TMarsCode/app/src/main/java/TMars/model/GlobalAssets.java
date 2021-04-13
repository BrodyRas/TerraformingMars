package TMars.model;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GlobalAssets {
    private Corporation[] corps;
    private Card[] cards;

    private static GlobalAssets instance;

    public static GlobalAssets getInstance(Context context){
        if(instance == null) instance = new GlobalAssets(context);
        return instance;
    }

    private GlobalAssets(Context context)
    {
        AssetManager assets = context.getAssets();
        try
        {
            InputStream is = assets.open("corperations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            corps = deserialize(json, Corporation[].class);

            is = assets.open("cards.json");
            size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            cards = deserialize(json, GreenCard[].class);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Corporation getCorporation(int id)
    {
        return corps[id];
    }

    public Card getCard(String id)
    {
        for (int i = 0; i < cards.length; ++i)
        {
            if (cards[i].getId().equals(id)) return cards[i];
        }
        return null;
    }

    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }
}
