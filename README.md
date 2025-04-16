## 🧪 Exercises Overview

### Exercise 1: List Directory Alphabetically
- Lists the content of a given directory in alphabetical order (non-recursive).
- Uses: `DirectoryUtils.listDirectoryWithContents(...)`

### Exercise 2: Tree-Like Directory Display
- Displays the directory contents recursively.
- Shows whether an item is a file (F) or directory (D).
- Displays the last modified date for each file/folder.

### Exercise 3: Export Directory Listing to .txt
- Instead of printing to console, the directory content is written to a `.txt` file.
- Uses: `DirectoryExporter.exportDirectoryToFile(...)`

### Exercise 4: Read .txt Files
- Reads any `.txt` file and prints its content line by line to the console.
- Uses: `FileReaderUtil.printFileContent(...)`

### Exercise 5: Serialize and Deserialize Directory Info
- Creates `DirectoryItem` objects representing each file/folder.
- Serializes a list of these objects to a `.ser` file.
- Can later deserialize and display the objects from the `.ser` file.
- Uses:
    - `DirectorySerializer.serializeDirectory(...)`
    - `DirectorySerializer.deserializeAndPrint(...)`

---

## 💻 How to Compile

From the project root:

```bash
javac -d out src/*.java
```

This compiles all source files into the `out/` folder.

---

## ▶️ How to Run Exercises

All commands are to be run from the project root:

### Exercise 1 & 2 – Show directory contents:
```bash
java -cp out Main example_directory
```

### Exercise 3 – Export to .txt:
```bash
java -cp out Main example_directory output/result.txt
```

### Exercise 4 – Read a .txt file:
```bash
java -cp out Main inputs/example.txt
```

### Exercise 5 – Serialize directory to .ser:
```bash
java -cp out Main example_directory output/directory_data.ser
```

### Exercise 5 – Deserialize and show content:
```bash
java -cp out Main output/directory_data.ser
```

---

## 📌 Notes

- Relative paths are used throughout the project to ensure portability.
- `File.separator` is used for cross-platform compatibility.
- All code is in English, formatted using clean code principles.
- No Maven or Gradle: pure Java project, compiled manually.

---
