/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Map;

public class OfficeLobby extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if("You have it!".equals(variables.get("CLEANING_SOLUTION")) && "You have it!".equals(variables.get("BLUE_CRYSTAL"))) {
            variables.put("CHARGED_CLEANING_SOLUTION", "You have it!");
            variables.remove("CLEANING_SOLUTION");
            JSONArray array = jsonObject.getJSONObject("alreadyVisited").getJSONArray("textLines");
            array.put(1, "You take a moment to touch the crystal against the side of the cleaning solution bottle.");
            array.put(2, "The cleaning solution now seems enchanted.");
        }
    }
}
