package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class DemonsFollowedYouHome extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if("You have it!".equals(variables.get("BLUE_CRYSTAL"))) {
            JSONArray textLines = jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines");
            textLines.put(5, textLines.get(4));
            textLines.put(4, "Why do your pockets feel lighter as if the crystal evaporated on your way home?");
            if("You have it!".equals(variables.get("CHARGED_CLEANING_SOLUTION"))) {
                textLines.put(6, "You reach for the cleaning solution.  But it has also lost its glow!");
            }
        }
    }
}
