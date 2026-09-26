/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import org.json.JSONObject;

import java.util.Map;

public class BlueCrystalAcquired extends CurrentRoom {

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        variables.put("BLUE_CRYSTAL", "You have it!");
    }
}
