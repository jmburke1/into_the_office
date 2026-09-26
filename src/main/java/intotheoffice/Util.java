/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.HexFormat;
import java.util.Map;
import java.util.Random;

public class Util {
    public static CurrentRoom getSpecificRoom(String specificRoom) {
        try {
            return (CurrentRoom) Class.forName("intotheoffice.specific_rooms." + specificRoom).getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void putRandomIntoMap(Map<String, String> variables, Random random) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(random);
            oos.close();
            baos.close();
            HexFormat hexFormat = HexFormat.of();
            variables.put("RANDOM", hexFormat.formatHex(baos.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Random pullRandomOutOfMap(Map<String, String> variables) {
        try {
            HexFormat hexFormat = HexFormat.of();
            byte[] serialized = hexFormat.parseHex(variables.get("RANDOM"));
            ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Random random = (Random)ois.readObject();
            ois.close();
            bais.close();
            return random;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
