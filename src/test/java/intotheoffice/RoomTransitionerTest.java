package intotheoffice;

import org.jline.utils.InfoCmp;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RoomTransitionerTest {
    private List<Object> collected;
    private int roomIncrementCount;

    @BeforeEach
    public void setup() {
        collected = new ArrayList<>();
        roomIncrementCount = -1; //transitioner.run("scene_outside_office_building"); takes it from -1 to 0 right at the beginning of the loop
    }

    @AfterEach
    public void tearDown() {
        collected.clear();
        roomIncrementCount = -1;
    }

    @ParameterizedTest(name = "[{index}] Json: {0}")
    @MethodSource("provideJsonObjectsViaClassLoader")
    public void doTest(JSONObject expectedContent) {
        JSONArray basedOnUserInputs = expectedContent.getJSONArray("basedOnUserInputs");
        RoomTransitioner transitioner = new RoomTransitioner(
                this::printText,
                ti -> arrayListUserInput(ti, basedOnUserInputs),
                () -> {
                    roomIncrementCount++;
                }
        );
        transitioner.run("scene_outside_office_building");
        JSONArray expectedOutputs = expectedContent.getJSONArray("expectedThingsPrinted");
        int i = 0;
        for(Object o : collected) {
            Assertions.assertEquals(expectedOutputs.getString(i), o.toString());
            i++;
        }

        //Uncomment this and comment out the other when adding tests.
        /*for(Object o : collected) {
            expectedOutputs.put(i, o.toString());
            i++;
        }
        System.out.println(expectedContent.toString(2));
        */
    }
    private void printText(Object o) {
        collected.add(o);
    }

    private String arrayListUserInput(String terminalIndicator, JSONArray basedOnUserInputs) {
        Assertions.assertEquals("> ", terminalIndicator);
        return basedOnUserInputs.getString(roomIncrementCount);
    }

    static Stream<JSONObject> provideJsonObjectsViaClassLoader() throws URISyntaxException {
        ClassLoader classLoader = RoomTransitionerTest.class.getClassLoader();
        URL resource = classLoader.getResource("expected_outputs");
        //URL resource = classLoader.getResource("under_construction"); //Uncomment this and comment out the other when adding tests.
        if (resource == null) {
            throw new IllegalArgumentException("Folder not found in test resources!");
        }
        File directory = new File(resource.toURI());
        File[] files = directory.listFiles();
        if (files == null) {
            return Stream.empty();
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .map(file -> RoomTransitionerTest.readString(Path.of(file.toString())))
                .map(JSONObject::new); // Exclude subdirectories
    }

    private static String readString(Path path) {
        try {
            return Files.readString(path);
        } catch(IOException ioex) {
            throw new RuntimeException(ioex);
        }
    }
}
