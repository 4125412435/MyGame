package MyGame;

import org.lwjgl.system.CallbackI;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static String loadResource(String path){
        String result;
        InputStream inputStream = Utils.class.getResourceAsStream(path);
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
        result = scanner.useDelimiter("\\A").next();
        return result;
    }
}
