import java.io.File;

public class AppRunner {

    public static void run(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        File target = new File(args[0]);

        // Deserialize from .ser
        if (args.length == 1 && args[0].endsWith(".ser")) {
            deserializeSerializedFile(target);
            return;
        }

        // Serialize directory to .ser
        if (args.length == 2 && args[1].endsWith(".ser")) {
            serializeDirectory(target, new File(args[1]));
            return;
        }

        // Export to .txt
        if (args.length == 2) {
            exportDirectoryToTxt(target, new File(args[1]));
            return;
        }

        // Read .txt content
        if (args.length == 1 && args[0].endsWith(".txt")) {
            readTxtFile(target);
            return;
        }

        // Display directory contents in console
        if (target.isDirectory()) {
            displayDirectory(target);
            return;
        }

        System.err.println(" Invalid input. Please try again.");
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java Main <directory>                         → display contents");
        System.out.println("  java Main <directory> <output.txt>            → save to .txt");
        System.out.println("  java Main <output.txt>                        → read from .txt");
        System.out.println("  java Main <directory> <output.ser>            → serialize directory");
        System.out.println("  java Main <output.ser>                        → deserialize and display");
    }

    private static void serializeDirectory(File dir, File outputFile) {
        try {
            DirectorySerializer.serializeDirectory(dir, outputFile);
        } catch (DirectoryException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }

    private static void deserializeSerializedFile(File file) {
        try {
            DirectorySerializer.deserializeAndPrint(file);
        } catch (DirectoryException e) {
            System.err.println("Deserialization error: " + e.getMessage());
        }
    }

    private static void exportDirectoryToTxt(File dir, File outputFile) {
        try {
            outputFile.getParentFile().mkdirs();
            DirectoryExporter.exportDirectoryToFile(dir, outputFile);
            System.out.println("Output written to: " + outputFile.getPath());
        } catch (DirectoryException e) {
            System.err.println("Export error: " + e.getMessage());
        }
    }

    private static void readTxtFile(File file) {
        try {
            FileReaderUtil.printFileContent(file);
        } catch (DirectoryException e) {
            System.err.println("Read error: " + e.getMessage());
        }
    }

    private static void displayDirectory(File dir) {
        try {
            DirectoryUtils.listDirectoryWithContents(dir);
        } catch (DirectoryException e) {
            System.err.println("Display error: " + e.getMessage());
        }
    }
}
