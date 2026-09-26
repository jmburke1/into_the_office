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
import org.jline.utils.InfoCmp;
import org.json.JSONObject;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainApp {

    public static void main(String[] args) throws Exception {
        // Initialize JLine terminal
        Terminal terminal = TerminalBuilder.terminal();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        // Create lambda functions for printing
        Consumer<Object> printText = text -> {
            if(text instanceof JSONObject jo) {
                String coloredText = new AttributedString(jo.getString("text"), AttributedStyle.DEFAULT.foreground(jo.getInt("jLineColor"))).toAnsi();
                terminal.writer().println(coloredText);
            } else {
                terminal.writer().println(text);
            }
        };

        // Create lambda function for user input
        Function<String, String> userInput = (prompt) -> {
            try {
                return lineReader.readLine(prompt).trim();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        // Create and run the RoomTransitioner
        RoomTransitioner transitioner = new RoomTransitioner(
                printText,
                userInput,
                () -> {
                    terminal.puts(InfoCmp.Capability.clear_screen);
                    terminal.flush();
                },
                new Random()
        );

        // Start the game with the initial room
        transitioner.run("scene_outside_office_building");

        // Clean up
        terminal.close();
    }
}