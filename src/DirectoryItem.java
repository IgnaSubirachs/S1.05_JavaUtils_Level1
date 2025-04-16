import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DirectoryItem implements Serializable {
    private String name;
    private String type; // "F" o "D"
    private LocalDateTime lastModified;
    private String relativePath;

    public DirectoryItem(String name, String type, LocalDateTime lastModified, String relativePath) {
        this.name = name;
        this.type = type;
        this.lastModified = lastModified;
        this.relativePath = relativePath;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public String getRelativePath() {
        return relativePath;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "(" + type + ") " + name + " - " + formatter.format(lastModified) + " [" + relativePath + "]";
    }
}
