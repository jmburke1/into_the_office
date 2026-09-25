package intotheoffice;

import org.json.JSONObject;

public class ChoiceFactory {
    private final JSONObject choiceJson;
    private final boolean wasAlreadyHere;

    public ChoiceFactory(JSONObject choiceJsonParam, boolean wasAlreadyHereParam) {
        choiceJson = choiceJsonParam;
        wasAlreadyHere = wasAlreadyHereParam;
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
                            choiceJson.getString("label")
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
