package intotheoffice;

import org.jline.utils.InfoCmp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void doTest() {
        List<String> userEntries = new ArrayList<>();
        userEntries.add("2");
        RoomTransitioner transitioner = new RoomTransitioner(
                this::printText,
                ti -> arrayListUserInput(ti, userEntries),
                () -> {
                    roomIncrementCount++;
                }
        );
        transitioner.run("scene_outside_office_building");
        for(Object o : collected) {
            System.out.println(o);
        }
    }
    private void printText(Object o) {
        collected.add(o);
    }

    private String arrayListUserInput(String terminalIndicator, List<String> userEntries) {
        Assertions.assertEquals("> ", terminalIndicator);
        return userEntries.get(roomIncrementCount);
    }
}
