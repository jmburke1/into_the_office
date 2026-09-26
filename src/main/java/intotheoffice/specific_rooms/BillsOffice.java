/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class BillsOffice extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        JSONArray choices = jsonObject.getJSONArray("choices");
        if(variables.containsKey("BLUE_CRYSTAL")) {
            choices.remove(0);
            choices.remove(0);
            choices.remove(0);
        } else {
            choices.remove(3);
            if(variables.containsKey("TRUTHFUL_COMBINATION")) {
                choices.getJSONObject(0).put("correctAnswer", variables.get("TRUTHFUL_COMBINATION"));
                choices.getJSONObject(1).put("correctAnswer", variables.get("TRUTHFUL_COMBINATION"));
            } else {
                choices.getJSONObject(1).put("nextRoom", "suitcase_eats_character");
            }
        }
    }
}
