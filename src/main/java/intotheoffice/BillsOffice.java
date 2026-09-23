package intotheoffice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class BillsOffice extends CurrentRoom {
    private Map<String, String> variables;

    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        this.variables = variables;
    }

    public List<Choice> getChoices() {
        if(variables.containsKey("BLUE_CRYSTAL")) {
            Choice choice = new Choice("1", "You already have what you came here for", "office_lobby");
            return List.of(choice);
        } else {
            Choice badChoice = new OfficeOfBillBadChoice(variables.get("TRUTHFUL_COMBINATION"));
            Choice goodChoice = new OfficeOfBillGoodChoice(variables.get("TRUTHFUL_COMBINATION"));
            Choice goBack = new Choice("GO_BACK", "Just go back for now.", "office_lobby");
            return List.of(badChoice, goodChoice, goBack);
        }
    }
}
