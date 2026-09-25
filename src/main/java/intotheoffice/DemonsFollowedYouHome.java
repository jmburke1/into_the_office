package intotheoffice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class DemonsFollowedYouHome extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if(variables.containsKey("BLUE_CRYSTAL") && variables.get("BLUE_CRYSTAL").equals("You have it!")) {
            JSONArray textLines = jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines");
            textLines.put(5, textLines.get(4));
            textLines.put(4, "Why do your pockets feel lighter as if the crystal evaporated on your way home?");
        }
    }
}
