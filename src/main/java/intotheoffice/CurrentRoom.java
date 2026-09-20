/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrentRoom {
    private final List<String> textLines;
    private final List<String> redTextLines;
    private final List<String> blueTextLines;
    private final String prompt;
    private final List<Choice> choices;

    public CurrentRoom(String json) {
        JSONObject jsonObject = new JSONObject(json);
        
        // Initialize textLines with default empty list
        this.textLines = new ArrayList<>();
        if (jsonObject.has("textLines")) {
            JSONArray textArray = jsonObject.getJSONArray("textLines");
            for (int i = 0; i < textArray.length(); i++) {
                this.textLines.add(textArray.getString(i));
            }
        }

        // Initialize redTextLines with default empty list
        this.redTextLines = new ArrayList<>();
        if (jsonObject.has("redTextLines")) {
            JSONArray redArray = jsonObject.getJSONArray("redTextLines");
            for (int i = 0; i < redArray.length(); i++) {
                this.redTextLines.add(redArray.getString(i));
            }
        }

        // Initialize blueTextLines with default empty list
        this.blueTextLines = new ArrayList<>();
        if (jsonObject.has("blueTextLines")) {
            JSONArray blueArray = jsonObject.getJSONArray("blueTextLines");
            for (int i = 0; i < blueArray.length(); i++) {
                this.blueTextLines.add(blueArray.getString(i));
            }
        }

        // Initialize prompt with default empty string
        this.prompt = jsonObject.optString("prompt", "");

        // Initialize choices with default empty list
        this.choices = new ArrayList<>();
        if (jsonObject.has("choices")) {
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            for (int i = 0; i < choicesArray.length(); i++) {
                JSONObject choiceJson = choicesArray.getJSONObject(i);
                Choice choice = new Choice(
                        choiceJson.getString("id"),
                        choiceJson.getString("description"),
                        choiceJson.getString("nextRoom")
                );
                this.choices.add(choice);
            }
        }
    }

    public List<String> getTextLines() {
        return textLines;
    }

    public List<String> getRedTextLines() {
        return redTextLines;
    }

    public List<String> getBlueTextLines() {
        return blueTextLines;
    }

    public String getPrompt() {
        return prompt;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public boolean isTerminal() {
        return choices.isEmpty();
    }
}
