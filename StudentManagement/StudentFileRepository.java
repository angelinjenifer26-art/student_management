
import java.io.*;
import java.nio.file.*;

public class StudentFileRepository {

    private final Path dataFile;

    public StudentFileRepository(String fileName) {
        this.dataFile = Paths.get(fileName);
    }

    public void save(Object data) throws IOException {

        Path absoluteFile = dataFile.toAbsolutePath();
        Path parent = absoluteFile.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Path temporaryFile = Files.createTempFile(
            parent, "students-", ".tmp"
        );

        try {
            try (ObjectOutputStream output =
                    new ObjectOutputStream(
                        new BufferedOutputStream(
                            Files.newOutputStream(temporaryFile)))) {

                output.writeObject(data);
                output.flush();
            }

            try {
                Files.move(
                    temporaryFile,
                    absoluteFile,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
                );

            } catch (AtomicMoveNotSupportedException exception) {

                Files.move(
                    temporaryFile,
                    absoluteFile,
                    StandardCopyOption.REPLACE_EXISTING
                );
            }

        } finally {
            Files.deleteIfExists(temporaryFile);
        }
    }

    public Object load() throws IOException, ClassNotFoundException {

        if (!Files.exists(dataFile)) {
            return null;
        }

        try (ObjectInputStream input =
                new ObjectInputStream(
                    new BufferedInputStream(
                        Files.newInputStream(dataFile)))) {

            return input.readObject();
        }
    }
}
