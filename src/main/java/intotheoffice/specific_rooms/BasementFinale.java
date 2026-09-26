package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class BasementFinale extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        JSONArray array = jsonObject.getJSONArray("choices");
        if(!"You have it!".equals(variables.get("BLUE_CRYSTAL"))) {
            array.remove(0);
            array.getJSONObject(0).put("id", "1");
            array.getJSONObject(1).put("id", "2");
        } else {
            array.remove(1);
            array.getJSONObject(1).put("id", "2");
        }
    }
}
