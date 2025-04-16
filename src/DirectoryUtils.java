import java.io.File;
import java.util.Arrays;

public class DirectoryUtils {

    public static void listDirectoryWithContents(File directory) throws DirectoryException {
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

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("(D)"+file.getName() + "/");

                File[] subfiles = file.listFiles();
                if (subfiles != null && subfiles.length > 0) {
                    Arrays.sort(subfiles);
                    for (File subfile : subfiles) {
                        String type=subfile.isDirectory()?"(D)":"(F)";
                        System.out.println("    " + subfile.getName());
                    }
                } else {
                    System.out.println("    (empty folder)");
                }

            } else {
                System.out.println("(F)"+file.getName());
            }
        }
    }
}