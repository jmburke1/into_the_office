/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice.specific_rooms;

import intotheoffice.CurrentRoom;
import intotheoffice.Util;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ReceptionistDeskRoom extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if(!variables.containsKey("TRUTHFUL_COMBINATION")) {
            Random random = Util.pullRandomOutOfMap(variables);
            String truthfulCombination = combo(random);
            String lyingCombination;
            do {
                lyingCombination = combo(random);
            } while(lyingCombination.equals(truthfulCombination));
            variables.put("TRUTHFUL_COMBINATION", truthfulCombination);
            variables.put("LYING_COMBINATION", lyingCombination);
        }
        JSONArray array = jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines");
        array.put(1, array.getString(1).replace("$TRUTHFUL_COMBINATION", variables.get("TRUTHFUL_COMBINATION")));
        array = jsonObject.getJSONObject("alreadyVisited").getJSONArray("textLines");
        String lyingCombo = variables.get("LYING_COMBINATION");
        if(variables.containsKey("BLUE_CRYSTAL")) {
            lyingCombo = ": We have other ways!";
        }
        JSONObject toBeTextReplaced = array.getJSONObject(1);
        toBeTextReplaced.put("text", toBeTextReplaced.getString("text").replace("$LYING_COMBINATION", lyingCombo));
    }

    private String combo(Random random) {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        Collections.shuffle(list, random);
        return String.join("", list);
    }
}
