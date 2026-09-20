/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

public class Choice {
    private final String id;
    private final String description;
    private final String nextRoom;

    public Choice(String id, String description, String nextRoom) {
        this.id = id;
        this.description = description;
        this.nextRoom = nextRoom;
    }

    public String getIdDescription() {
        return id + ". " + description;
    }

    public String getNextRoom() {
        return nextRoom;
    }

    public boolean idEquals(String input) {
        return id.equals(input);
    }
}
