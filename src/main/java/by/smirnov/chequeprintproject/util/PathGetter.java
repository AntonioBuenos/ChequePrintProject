package by.smirnov.chequeprintproject.util;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class PathGetter {

    private static final String DIR = "user.dir";
    private static final String PATH = "src" +
            File.separator + "main" +
            File.separator + "resources" +
            File.separator;

    public static String getPath(String filename) {
        String root = System.getProperty(DIR);
        return root + File.separator + PATH + filename;
    }
}
