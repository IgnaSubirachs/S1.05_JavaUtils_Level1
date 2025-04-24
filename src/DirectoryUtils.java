import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DirectoryUtils {

    public static void listDirectory(File directory, int level, PrintWriter writer) throws DirectoryException {
        if (directory == null || !directory.exists()) {
            throw new DirectoryException("The path does not exist.");
        }

        if (!directory.isDirectory()) {
            throw new DirectoryException("The path is not a directory.");
        }

        File[] files = directory.listFiles();
        if (files == null) {
            throw new DirectoryException("Error reading directory contents.");
        }

        Arrays.sort(files);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for (File file : files) {
            String type = file.isDirectory() ? "(D)" : "(F)";
            String line = "  ".repeat(level) + type + " " + file.getName() + " - " + sdf.format(new Date(file.lastModified()));

            if (writer != null) {
                writer.println(line);
            } else {
                System.out.println(line);
            }

            if (file.isDirectory()) {
                listDirectory(file, level + 1, writer);
            }
        }
    }

    public static void listDirectory(File directory) throws DirectoryException {
        listDirectory(directory, 0, null);
    }
}
