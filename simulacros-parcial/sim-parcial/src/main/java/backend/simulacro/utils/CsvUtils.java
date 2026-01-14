package backend.simulacro.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvUtils {

    private static final String FILES_FOLDER = "src/main/resources/files/";
    private static final String SEPARATOR = ",";

    public static List<String[]> readCsv(String fileName) {
        try {
            Path path =  Path.of(FILES_FOLDER + fileName);
            return Files.readAllLines(path)
            .stream()
            .skip(1)
            .map(line -> line.split(SEPARATOR))
            .toList();
        } catch (Exception e) {
            throw new RuntimeException("ERROR CARGANDO EL ARCHIVO", e);
        }
    }    
}
