package intotheoffice;

public class OfficeOfBillGoodChoice extends Choice {
    private String correctCombo;

    public OfficeOfBillGoodChoice(String correctCombo) {
        super("ENTERTEXT: ", "<The Correct Combination>", "office_of_bill_cleared");
        this.correctCombo = correctCombo;
    }

    public String getIdDescription() {
        return super.getIdDescription().replace(". ", "");
    }

    public boolean idEquals(String input) {
        if(input.startsWith("ENTERTEXT: ")) {
            return input.length() > 11 && input.substring(11).equals(correctCombo);
        }
        return false;
    }
}
