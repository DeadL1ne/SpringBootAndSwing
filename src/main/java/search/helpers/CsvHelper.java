package search.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.entity.Document;
import search.entity.KeyWord;
import search.service.impl.DocumentService;
import search.service.impl.KeyWordService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CsvHelper {
    private final String PATH_TO_CSV_FILE = "src/main/java/search/csv/DatabaseMock.csv";

    public Map<String, List<String>> getCsvData() throws IOException {
        HashMap<String, List<String>> fileKeys = new HashMap<>();
        List<String> csvRows = Files.readAllLines(Paths.get(PATH_TO_CSV_FILE).toAbsolutePath());
        csvRows = csvRows.stream().filter(i -> !i.equals(",")).collect(Collectors.toList());


        // we ignore first row because it is title of csv (That's why loop begin from 1)
        for (int i = 1; i < csvRows.size(); i++) {
            String[] splited = csvRows.get(i).split(",\""); // we get name of file and keys
            if (splited.length >= 2) {
                String filename = splited[0];
                // after splitting there are " symbol in the end of line
                List<String> keys = parseKeys(splited[1].replace("\"", ""));
                fileKeys.put(filename, keys);
            }
        }
        return fileKeys;
    }

    private List<String> parseKeys(String keys) {
        HashSet<String> uniqueKeys = new HashSet<>();
        String[] parsedKeys = keys.split(",");
        for (String key : parsedKeys) {
            uniqueKeys.add(key.trim().toLowerCase());
        }
        return new ArrayList<>(uniqueKeys);
    }
}
