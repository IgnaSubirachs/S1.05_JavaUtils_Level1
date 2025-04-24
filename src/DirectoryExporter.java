import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DirectoryExporter {

    public static void exportDirectoryToFile(File directory, File outputFile) throws DirectoryException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            DirectoryUtils.listDirectory(directory, 0, writer);
            System.out.println("✅ Directory exported to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            throw new DirectoryException("Error writing to file: " + e.getMessage());
        }
    }
}
