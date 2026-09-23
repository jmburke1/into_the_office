/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Set;
import java.util.HashSet;

public class RoomTransitioner {
    private final Consumer<String> printText;
    private final Consumer<String> printRedText;
    private final Consumer<String> printYellowText;
    private final Consumer<String> printBlueText;
    private final Function<String, String> userInput;
    private final Set<String> alreadyVisitedRooms;

    public RoomTransitioner(Consumer<String> printText, Consumer<String> printRedText,
                            Consumer<String> printYellowText, Consumer<String> printBlueText,
                            Function<String, String> userInput) {
        this.printText = printText;
        this.printRedText = printRedText;
        this.printYellowText = printYellowText;
        this.printBlueText = printBlueText;
        this.userInput = userInput;
        alreadyVisitedRooms = new HashSet<>();
    }

    public void run(String startRoom) {
        String currentRoomName = startRoom;
        
        while (true) {
            // Load the JSON file for the current room
            String json = loadRoomJson(currentRoomName);
            CurrentRoom currentRoom = new CurrentRoom(json, alreadyVisitedRooms.contains(currentRoomName));

            // Print the room's text lines
            for (String line : currentRoom.getTextLines()) {
                printText.accept(line);
            }
            
            // Print red text lines
            for (String line : currentRoom.getRedTextLines()) {
                printRedText.accept(line);
            }

            // Print yellow text lines
            for (String line : currentRoom.getYellowTextLines()) {
                printYellowText.accept(line);
            }

            // Print blue text lines
            for (String line : currentRoom.getBlueTextLines()) {
                printBlueText.accept(line);
            }
            
            // Check if this is a terminal room
            if (currentRoom.isTerminal()) {
                break;
            }
            
            // Get the next room name
            alreadyVisitedRooms.add(currentRoomName);
            currentRoomName = getNextRoom(currentRoom);
        }
    }
    
    private String getNextRoom(CurrentRoom currentRoom) {
        // Print the prompt
        if (!currentRoom.getPrompt().isEmpty()) {
            printText.accept(currentRoom.getPrompt());
        }
        
        // Print the choices
        for (Choice choice : currentRoom.getChoices()) {
            printText.accept(choice.getIdDescription());
        }
        
        // Get user input in a loop until a valid choice is made
        while (true) {
            String input = userInput.apply("> ");
            
            // Check if the input is a valid choice
            for (Choice choice : currentRoom.getChoices()) {
                if (choice.idEquals(input)) {
                    return choice.getNextRoom();
                }
            }
            
            // If we get here, the input was invalid
            printText.accept("Please choose a valid option.");
        }
    }
    
    private String loadRoomJson(String roomName) {
        // Load the JSON file from resources
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(roomName + ".json");
        
        if (inputStream == null) {
            throw new RuntimeException("Room JSON file not found: " + roomName + ".json");
        }
        
        // Read the JSON content
        java.util.Scanner scanner = new java.util.Scanner(inputStream);
        StringBuilder json = new StringBuilder();
        while (scanner.hasNextLine()) {
            json.append(scanner.nextLine());
        }
        scanner.close();
        
        return json.toString();
    }
}