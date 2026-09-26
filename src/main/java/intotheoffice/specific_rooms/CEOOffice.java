/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Map;

public class CEOOffice extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        variables.put("CEO_GLASSES", "You have it!");
        JSONArray array = jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines");
        String templateString = array.getString(2);
        if("You have it!".equals(variables.get("CHARGED_CLEANING_SOLUTION"))) {
            array.put(2, templateString.replace("$ZOMBIE_BLAST", " after blasting another zombie with the enchanted cleaning solution!"));
        }
        if("A paperweight now!".equals(variables.get("BLUE_CRYSTAL"))) {
            array.put(2, templateString.replace("$ZOMBIE_BLAST", ".  Somehow, the invasion hasn't touched this room either..."));
        }
    }
}
