package TMarsTest;

import com.google.gson.Gson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import TMars.model.Corporation;
import TMars.providers.TagProvider;

public class corporationTest {

    public static String serialize(Object requestInfo) {
        return (new Gson()).toJson(requestInfo);
    }

    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

    Corporation corp;

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
        corp.setProduction(map2);


    }

    @Test
    public void testSerial() {
        // Set the double to return the specified response when it gets the specified request
        String out = serialize(corp);
        Corporation result = deserialize(out, Corporation.class);
        Assertions.assertEquals(corp.getName(), result.getName());
    }
}
