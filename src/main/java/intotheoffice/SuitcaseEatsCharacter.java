package intotheoffice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class SuitcaseEatsCharacter extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if(variables.containsKey("MOST_RECENT_COMBO") && variables.get("MOST_RECENT_COMBO").equals("TRUTHFUL_COMBINATION")) {
            jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines").put(4, new JSONObject("{\"text\": \"Nice try!!!\", \"jLineColor\": 1}"));
        }
    }
}
