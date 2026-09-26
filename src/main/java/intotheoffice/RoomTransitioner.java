/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Set;
import java.util.HashSet;

public class RoomTransitioner {
    private final Consumer<Object> printText;
    private final Function<String, String> userInput;
    private final Set<String> alreadyVisitedRooms;
    private final Runnable doWhenNextRoom;
    private final Map<String, String> variables;

    public RoomTransitioner(Consumer<Object> printText,
                            Function<String, String> userInput, Runnable doWhenNextRoom) {
        this.printText = printText;
        this.userInput = userInput;
        this.doWhenNextRoom = doWhenNextRoom;
        alreadyVisitedRooms = new HashSet<>();
        variables = new HashMap<>();
    }

    public void run(String startRoom) {
        String currentRoomName = startRoom;
        
        while (true) {
            doWhenNextRoom.run();
            // Load the JSON file for the current room
            JSONObject json = new JSONObject(loadRoomJson(currentRoomName));
            CurrentRoom currentRoom;
            if(json.has("specificRoom")) {
                currentRoom = Util.getSpecificRoom(json.getString("specificRoom"));
            } else {
                currentRoom = new CurrentRoom();
            }
            currentRoom.init(json, alreadyVisitedRooms.contains(currentRoomName), variables);

            // Print the room's text lines
            for (Object line : currentRoom.getTextLines()) {
                printText.accept(line);
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
                .getResourceAsStream("room_jsons/" + roomName + ".json");
        
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