/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import java.util.Map;

public class FillInTheBlankRight extends Choice {
    private String correctAnswer;
    private Map<String, String> variables;

    public FillInTheBlankRight(
            String correctAnswer,
            String nextRoom,
            String answerTypeLabel,
            Map<String, String> variables) {
        super("ENTERTEXT: ", "<The Correct "+answerTypeLabel+">", nextRoom);
        this.correctAnswer = correctAnswer;
        this.variables = variables;
    }

    public String getIdDescription() {
        return super.getIdDescription().replace(". ", "");
    }

    public boolean idEquals(String input) {
        if(input.startsWith("ENTERTEXT: ")) {
            boolean returnThis = input.length() > 11 && input.substring(11).equals(correctAnswer);
            if(returnThis) {
                variables.put("MOST_RECENT_COMBO", correctAnswer);
            }
            return returnThis;
        }
        return false;
    }
}
