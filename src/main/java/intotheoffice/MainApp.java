/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class MainApp {

    public static void main(String[] args) throws Exception {
        // Initialize JLine terminal
        Terminal terminal = TerminalBuilder.terminal();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        // Display the initial scene
        terminal.writer().println("You are standing outside the office building.");
        terminal.writer().println("The door is slightly ajar, revealing a dimly lit interior.");
        terminal.writer().println();

        // Display choices
        terminal.writer().println("What do you want to do?");
        terminal.writer().println("1. Enter the office");
        terminal.writer().println("2. Go home");

        // Get user input
        String choice = lineReader.readLine("> ");
        
        if (choice != null && choice.trim().equals("1")) {
            terminal.writer().println();
            terminal.writer().println("You push open the door and step inside.");
            terminal.writer().println("As you cross the threshold, you feel a cold wind blow through the building.");
            terminal.writer().println("Suddenly, you are possessed by demons!");
            terminal.writer().println();
            terminal.writer().println("Game Over - You were possessed by demons!");
        } else if (choice != null && choice.trim().equals("2")) {
            terminal.writer().println();
            terminal.writer().println("You decide it's better to be safe than sorry.");
            terminal.writer().println("You turn around and head home.");
            terminal.writer().println("The next day, you're sipping coffee on your porch, safe from any demon possession.");
            terminal.writer().println();
            terminal.writer().println("Game Over - You chose to go home and survived!");
        } else {
            terminal.writer().println();
            terminal.writer().println("Please choose a valid option (1 or 2).");
            terminal.writer().println("Game Over - Invalid choice!");
        }

        // Clean up
        terminal.close();
    }
}
