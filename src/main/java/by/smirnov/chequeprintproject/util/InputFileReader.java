package by.smirnov.chequeprintproject.util;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class InputFileReader {

    public static List<String> readFile(String path){
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }
}
