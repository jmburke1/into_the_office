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
    private List<Object> textLines;
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
                this.textLines.add(textArray.get(i));
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
                Choice choice = (new ChoiceFactory(choiceJson, wasAlreadyHere, variables)).createChoice();
                if(choice != null) {
                    this.choices.add(choice);
                }
            }
        }
    }

    public List<Object> getTextLines() {
        return textLines;
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

    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
    }
}
