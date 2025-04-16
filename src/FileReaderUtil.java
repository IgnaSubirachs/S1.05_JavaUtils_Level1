import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {
    public static void printFileContent(File file) throws DirectoryException {
        if (!file.exists()) {
            throw new DirectoryException("The file does not exist" + file.getPath());
        }
        if (!file.isFile()) {
            throw new DirectoryException("The file is not a valid file" + file.getPath());
        }
        System.out.println("File content: " + file.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new DirectoryException("Ereror reading file:" + e.getMessage());
        }
    }


}
