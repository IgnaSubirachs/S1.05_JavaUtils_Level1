import java.io.File;

public class AppRunner {

    public static void run(String[] args) {
        File directoryFile = null;

        if (args.length < 1) {
            printUsage();
            String defaultPath = System.getProperty("user.dir") + "/example_directory";
            directoryFile = new File(defaultPath);
            System.out.println("No path specified. Using: " + defaultPath);
            displayDirectory(directoryFile);
            return;
        }

        File target = new File(args[0]);

        if (args.length == 1) {
            if (args[0].endsWith(".ser")) {
                deserializeSerializedFile(target);
                return;
            }

            if (args[0].endsWith(".txt")) {
                readTxtFile(target);
                return;
            }

            if (target.isDirectory()) {
                displayDirectory(target);
                return;
            }

            System.err.println("Invalid input. Please provide a directory, .txt or .ser file.");
            return;
        }

        if (args.length == 2) {
            if (args[1].endsWith(".ser")) {
                serializeDirectory(target, new File(args[1]));
                return;
            }

            if (args[1].endsWith(".txt")) {
                exportDirectoryToTxt(target, new File(args[1]));
                return;
            }
        }

        System.err.println("⚠️ Invalid input. See usage below.");
        printUsage();
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
            System.out.println("✅ Output written to: " + outputFile.getPath());
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
            DirectoryUtils.listDirectory(dir);
        } catch (DirectoryException e) {
            System.err.println("Display error: " + e.getMessage());
        }
    }
}
