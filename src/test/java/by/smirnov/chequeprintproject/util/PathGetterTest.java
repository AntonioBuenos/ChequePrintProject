package by.smirnov.chequeprintproject.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathGetterTest {

    @Test
    void getPathTest() {
        String expected = "D:\\Java\\Anything\\ChequePrintProject\\src\\main\\resources\\Cheques.txt";
        String actual = PathGetter.getPath("Cheques.txt");
        assertEquals(expected, actual);
    }
}
