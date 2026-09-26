/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.json.JSONObject;

import java.util.Map;

public class ChoiceFactory {
    private final JSONObject choiceJson;
    private final boolean wasAlreadyHere;
    private final Map<String, String> variables;

    public ChoiceFactory(JSONObject choiceJsonParam, boolean wasAlreadyHereParam, Map<String, String> variablesParam) {
        choiceJson = choiceJsonParam;
        wasAlreadyHere = wasAlreadyHereParam;
        variables = variablesParam;
    }

    public Choice createChoice() {
        String wanted = wasAlreadyHere ? "subsequentVisit" : "firstTimeVisit";
        if(choiceJson.optString("visitedRoomsStatus", wanted).equals(wanted)) {
            Choice choice = new Choice(
                    choiceJson.optString("id"),
                    choiceJson.optString("description"),
                    choiceJson.getString("nextRoom")
            );
            if(choiceJson.has("choiceType")) {
                String choiceType = choiceJson.getString("choiceType");
                if(choiceType.equals("FILL_IN_THE_BLANK_RIGHT")) {
                    choice = new FillInTheBlankRight(
                            choiceJson.getString("correctAnswer"),
                            choiceJson.getString("nextRoom"),
                            choiceJson.getString("label"),
                            variables
                    );
                }
                if(choiceType.equals("FILL_IN_THE_BLANK_WRONG")) {
                    choice = new FillInTheBlankWrong(
                            choiceJson.getString("correctAnswer"),
                            choiceJson.getString("nextRoom")
                    );
                }
            }
            return choice;
        }
        return null;
    }
}
