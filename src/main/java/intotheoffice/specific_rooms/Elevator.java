/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class Elevator extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        JSONArray array = jsonObject.getJSONArray("choices");
        if("You have it!".equals(variables.get("CEO_GLASSES"))) {
            array.remove(2);
            array.getJSONObject(2).put("id", "3");
        } else {
            array.remove(3);
        }
    }
}
