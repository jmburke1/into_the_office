/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

public class FillInTheBlankWrong extends Choice {
    private String correctAnswer;

    public FillInTheBlankWrong(String correctCombo, String nextRoom) {
        super("ENTERTEXT: ", "<Your Wild Guess>", nextRoom);
        this.correctAnswer = correctCombo;
    }

    public String getIdDescription() {
        return super.getIdDescription().replace(". ", "");
    }

    public boolean idEquals(String input) {
        if(input.startsWith("ENTERTEXT: ")) {
            return input.length() == 11 || !input.substring(11).equals(correctAnswer);
        }
        return false;
    }
}
