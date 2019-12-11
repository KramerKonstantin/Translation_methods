import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class RunTests {
    private void runTest(String path, String test) {
        Parser parser = new Parser();
        try {
            Visualizer.visualizer(parser.parse(new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))), test);
        } catch (ParseException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testForMe() {
        runTest("test/testForMe.txt", "testForMe");
    }

    @Test
    public void testVariable() {
        runTest("test/testVariable.txt", "testVariable");
    }

    @Test
    public void testNot() {
        runTest("test/testNot.txt", "testNot");
    }

    @Test
    public void testAnd() {
        runTest("test/testAnd.txt", "testAnd");
    }

    @Test
    public void testXor() {
        runTest("test/testXor.txt", "testXor");
    }

    @Test
    public void testOr() {
        runTest("test/testOr.txt", "testOr");
    }

    @Test
    public void testBrackets() {
        runTest("test/testBrackets.txt", "testBrackets");
    }

    @Test
    public void testVariableException() {
        runTest("test/testVariableException.txt", "testVariableException");
    }

    @Test
    public void testNotException() {
        runTest("test/testNotException.txt", "testNotException");
    }

    @Test
    public void testAndException() {
        runTest("test/testAndException.txt", "testAndException");
    }

    @Test
    public void testXorException() {
        runTest("test/testXorException.txt", "testXorException");
    }

    @Test
    public void testOrException() {
        runTest("test/testOrException.txt", "testOrException");
    }

    @Test
    public void testBracketsException() {
        runTest("test/testBracketsException.txt", "testBracketsException");
    }

    @Test
    public void testEnd() {
        runTest("test/testEnd.txt", "testEnd");
    }

    @Test
    public void testEndException() {
        runTest("test/testEndException.txt", "testEndException");
    }

    @Test
    public void testPriority() {
        runTest("test/testPriority.txt", "testPriority");
    }
}
