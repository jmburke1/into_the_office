package intotheoffice;

public class OfficeOfBillBadChoice extends Choice {
    private String correctCombo;

    public OfficeOfBillBadChoice(String correctCombo) {
        super("ENTERTEXT: ", "<Your Wild Guess>", "suitcase_eats_character");
        this.correctCombo = correctCombo;
    }

    public String getIdDescription() {
        return super.getIdDescription().replace(". ", "");
    }

    public boolean idEquals(String input) {
        if(input.startsWith("ENTERTEXT: ")) {
            return input.length() == 11 || !input.substring(11).equals(correctCombo);
        }
        return false;
    }
}
