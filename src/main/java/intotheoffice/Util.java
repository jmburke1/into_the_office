package intotheoffice;

public class Util {
    public static CurrentRoom getSpecificRoom(String specificRoom) {
        try {
            return (CurrentRoom) Class.forName("intotheoffice." + specificRoom).getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
