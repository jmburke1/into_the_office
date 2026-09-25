package intotheoffice;

import org.json.JSONObject;

import java.util.Map;

public class RestRoom extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        variables.put("CLEANING_SOLUTION", "You have it!");
    }
}
