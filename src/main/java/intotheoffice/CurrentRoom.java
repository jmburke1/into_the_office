/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurrentRoom {
    private List<String> textLines;
    private List<String> redTextLines;
    private List<String> yellowTextLines;
    private List<String> blueTextLines;
    private String prompt;
    private List<Choice> choices;

    public void init(JSONObject jsonObject, boolean wasAlreadyHere, Map<String, String> variables) {
        respondToVariables(jsonObject, variables);
        
        // Initialize textLines with default empty list
        JSONObject textToPrint;
        if(wasAlreadyHere) {
            textToPrint = jsonObject.getJSONObject("alreadyVisited");
        } else {
            textToPrint = jsonObject.getJSONObject("brandNewVisit");
        }
        this.textLines = new ArrayList<>();
        if (textToPrint.has("textLines")) {
            JSONArray textArray = textToPrint.getJSONArray("textLines");
            for (int i = 0; i < textArray.length(); i++) {
                this.textLines.add(textArray.getString(i));
            }
        }

        // Initialize yellowTextLines with default empty list
        this.yellowTextLines = new ArrayList<>();
        if (textToPrint.has("yellowTextLines")) {
            JSONArray yellowArray = textToPrint.getJSONArray("yellowTextLines");
            for (int i = 0; i < yellowArray.length(); i++) {
                this.yellowTextLines.add(yellowArray.getString(i));
            }
        }

        // Initialize redTextLines with default empty list
        this.redTextLines = new ArrayList<>();
        if (textToPrint.has("redTextLines")) {
            JSONArray redArray = textToPrint.getJSONArray("redTextLines");
            for (int i = 0; i < redArray.length(); i++) {
                this.redTextLines.add(redArray.getString(i));
            }
        }

        // Initialize blueTextLines with default empty list
        this.blueTextLines = new ArrayList<>();
        if (textToPrint.has("blueTextLines")) {
            JSONArray blueArray = textToPrint.getJSONArray("blueTextLines");
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
                if(
                        !choiceJson.has("availability") || (
                                !wasAlreadyHere && choiceJson.getString("availability").equals("firstTimeVisit")
                        ) || (
                                wasAlreadyHere && choiceJson.getString("availability").equals("subsequentVisit")
                        )
                ) {
                    Choice choice = new Choice(
                            choiceJson.getString("id"),
                            choiceJson.getString("description"),
                            choiceJson.getString("nextRoom")
                    );
                    this.choices.add(choice);
                }
            }
        }
    }

    public List<String> getTextLines() {
        return textLines;
    }

    public List<String> getRedTextLines() {
        return redTextLines;
    }

    public List<String> getYellowTextLines() {
        return yellowTextLines;
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
        return getChoices().isEmpty();
    }

    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
    }
}
