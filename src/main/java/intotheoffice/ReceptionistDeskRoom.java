package intotheoffice;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReceptionistDeskRoom extends CurrentRoom {
    @Override
    protected void respondToVariables(JSONObject jsonObject, Map<String, String> variables) {
        if(!variables.containsKey("TRUTHFUL_COMBINATION")) {
            String truthfulCombination = combo();
            String lyingCombination;
            do {
                lyingCombination = combo();
            } while(lyingCombination.equals(truthfulCombination));
            variables.put("TRUTHFUL_COMBINATION", truthfulCombination);
            variables.put("LYING_COMBINATION", lyingCombination);
        }
        JSONArray array = jsonObject.getJSONObject("brandNewVisit").getJSONArray("textLines");
        array.put(1, array.getString(1).replace("$TRUTHFUL_COMBINATION", variables.get("TRUTHFUL_COMBINATION")));
        array = jsonObject.getJSONObject("alreadyVisited").getJSONArray("yellowTextLines");
        String lyingCombo = variables.get("LYING_COMBINATION");
        if(variables.containsKey("BLUE_CRYSTAL")) {
            lyingCombo = ": We have other ways!";
        }
        array.put(0, array.getString(0).replace("$LYING_COMBINATION", lyingCombo));
    }

    private String combo() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        Collections.shuffle(list);
        return String.join("", list);
    }
}
