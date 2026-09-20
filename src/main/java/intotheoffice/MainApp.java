/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

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
        String choice = null;
        while(choice == null) {
            choice = lineReader.readLine("> ");

            if (choice != null && choice.trim().equals("1")) {
                terminal.writer().println();
                terminal.writer().println("You push open the door and step inside.");
                terminal.writer().println("As you cross the threshold, you feel a cold wind blow through the building.");
                terminal.writer().println("Your vision blurs and you black out!");
                terminal.writer().println();
                String redText = new AttributedString("You were possessed by demons!", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi();
                terminal.writer().println(redText);
            } else if (choice != null && choice.trim().equals("2")) {
                terminal.writer().println();
                terminal.writer().println("You decide it's better to be safe than sorry.");
                terminal.writer().println("You turn around and head home.");
                terminal.writer().println("The next day, you're sipping coffee on your porch, safe from any demon possession.");
                terminal.writer().println();
                String blueText = new AttributedString("You chose to go home and survived!", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)).toAnsi();
                terminal.writer().println(blueText);
            } else {
                terminal.writer().println();
                terminal.writer().println("Please choose a valid option (1 or 2).");
                choice = null;
            }
        }

        // Clean up
        terminal.close();
    }
}
