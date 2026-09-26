/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONObject;

import java.util.Map;

public class SuitcaseEatsCharacter extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if("TRUTHFUL_COMBINATION".equals(variables.get("MOST_RECENT_COMBO"))) {
            jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines").put(4, new JSONObject("{\"text\": \"Nice try!!!\", \"jLineColor\": 1}"));
        }
    }
}
