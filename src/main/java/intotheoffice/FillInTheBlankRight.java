package intotheoffice;

public class FillInTheBlankRight extends Choice {
    private String correctAnswer;

    public FillInTheBlankRight(String correctAnswer, String nextRoom, String answerTypeLabel) {
        super("ENTERTEXT: ", "<The Correct "+answerTypeLabel+">", nextRoom);
        this.correctAnswer = correctAnswer;
    }

    public String getIdDescription() {
        return super.getIdDescription().replace(". ", "");
    }

    public boolean idEquals(String input) {
        if(input.startsWith("ENTERTEXT: ")) {
            return input.length() > 11 && input.substring(11).equals(correctAnswer);
        }
        return false;
    }
}
