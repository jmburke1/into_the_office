package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Map;

public class CubicleFarm  extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        JSONArray array = jsonObject.getJSONArray("choices");
        if(!"You have it!".equals(variables.get("BLUE_CRYSTAL"))) {
            array.remove(2);
            array.remove(2);
            array.getJSONObject(2).put("id", "3");
            if(!"You have it!".equals(variables.get("CLEANING_SOLUTION"))) {
                array.remove(2);
            }
        } else {
            array.remove(4);
            if(!"You have it!".equals(variables.get("CHARGED_CLEANING_SOLUTION"))) {
                array.remove(3);
            } else {
                array.remove(2);
                array.getJSONObject(2).put("id", "3");
            }
        }
    }
}
