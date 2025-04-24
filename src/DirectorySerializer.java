import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectorySerializer {

    public static void serializeDirectory(File directory, File outputFile) throws DirectoryException {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new DirectoryException("Invalid directory");
        }

        List<DirectoryItem> items = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files == null) {
            throw new DirectoryException("Error reading directory");
        }

        Arrays.sort(files);

        for (File file : files) {
            String type = file.isDirectory() ? "D" : "F";
            LocalDateTime lastModified = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());

            DirectoryItem item = new DirectoryItem(
                    file.getName(),
                    type,
                    lastModified,
                    directory.getName() + "/" + file.getName()
            );

            items.add(item);
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            out.writeObject(items);
        } catch (IOException e) {
            throw new DirectoryException("Error serializing file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void deserializeAndPrint(File inputFile) throws DirectoryException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(inputFile))) {
            List<DirectoryItem> items = (List<DirectoryItem>) in.readObject();
            for (DirectoryItem item : items) {
                System.out.println(item);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new DirectoryException("Error deserializing file: " + e.getMessage());
        }
    }
}
