package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class PulledIntoHole extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        JSONArray array = jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines");
        if(!"You have it!".equals(variables.get("BLUE_CRYSTAL"))) {
            array.remove(4);
            array.remove(4);
            array.remove(4);
        }
    }
}
