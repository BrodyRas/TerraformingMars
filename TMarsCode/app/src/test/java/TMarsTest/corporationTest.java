package TMarsTest;

import com.google.gson.Gson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import TMars.model.Card;
import TMars.model.Corporation;
import TMars.model.GreenCard;
import TMars.model.Score;
import TMars.providers.TagProvider;

public class corporationTest {

    public static String serialize(Object requestInfo) {
        return (new Gson()).toJson(requestInfo);
    }

    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

    Corporation corp;
    Card card;

    @BeforeEach
    public void setup()
    {
        corp = new Corporation();
        corp.setName("ecoline");
        //tags
        HashMap<TagProvider.CardTag, Integer> map = new HashMap<TagProvider.CardTag, Integer>();
        map.put(TagProvider.CardTag.Plant,1);
        corp.setTags(map);
        //resources
        HashMap<TagProvider.ResourceTag, Integer> map2 = new HashMap<TagProvider.ResourceTag, Integer>();
        map2.put(TagProvider.ResourceTag.Credits,36);
        map2.put(TagProvider.ResourceTag.Plant,3);
        corp.setResources(map2);
        //production
        map2 = new HashMap<TagProvider.ResourceTag, Integer>();
        map2.put(TagProvider.ResourceTag.Plant, 2);
        corp.setProductions(map2);

        card = new GreenCard();
        card.setName("Domed Crater");
        card.setId("016");
        card.setPoints(new Score(1,0,0));
        card.setCost(24);
        map = new HashMap<TagProvider.CardTag, Integer>();
        map.put(TagProvider.CardTag.City,1);
        map.put(TagProvider.CardTag.Building,1);
        card.setTags(map);
        map2 = new HashMap<TagProvider.ResourceTag, Integer>();
        map2.put(TagProvider.ResourceTag.Plant,3);
        card.setResources(map2);
        map2 = new HashMap<TagProvider.ResourceTag, Integer>();
        map2.put(TagProvider.ResourceTag.Energy, -1);
        map2.put(TagProvider.ResourceTag.Credits, 3);
        card.setProductions(map2);
    }

    @Test
    public void testSerial() {
        // Set the double to return the specified response when it gets the specified request
        String out = serialize(corp);
        Corporation result = deserialize(out, Corporation.class);
        Assertions.assertEquals(corp.getName(), result.getName());
        Assertions.assertEquals(0,result.getProductions().get(TagProvider.ResourceTag.Energy));
    }

    @Test
    public void testSerial2() {
        // Set the double to return the specified response when it gets the specified request
        String out = serialize(card);
        Card result = deserialize(out, GreenCard.class);
        Assertions.assertEquals(card.getName(), result.getName());
        Assertions.assertEquals(0,result.getProductions().get(TagProvider.ResourceTag.Energy));
    }
}
