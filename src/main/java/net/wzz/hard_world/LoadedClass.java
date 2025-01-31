package net.wzz.hard_world;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LoadedClass {
    public static void copyConfigFile() {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(Objects.requireNonNull(HardWorldMod.class.getResourceAsStream("/HardWorldProperties.json")));
            if (Files.exists(Path.of("HardWorldProperties.json"))) {
                return;
            }
            Path destinationDir = Paths.get("").toAbsolutePath().getParent();
            Files.createDirectories(destinationDir);
            Path destinationFile = Path.of("HardWorldProperties.json");
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile.toFile()));
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, bytesRead);
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setup(FMLCommonSetupEvent event) {
        try {
            Class.forName("org.json.JSONException");
            Class.forName("org.json.JSONObject");
            Class.forName("org.json.JSONObject$Null");
            Class.forName("org.json.JSONPointerException");
            Class.forName("org.json.JSONArray");
            Class.forName("org.json.JSONString");
            Class.forName("org.json.JSONTokener");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
