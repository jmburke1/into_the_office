package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONObject;

import java.util.Map;

public class CrystalDepleted extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        variables.put("BLUE_CRYSTAL", "A paperweight now!");
    }
}
