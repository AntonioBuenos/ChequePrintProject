package by.smirnov.chequeprintproject.util;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class PathGetter {

    public static String getPath(String filename) {
        String root = System.getProperty("user.dir");
        return root + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + filename;
    }
}
