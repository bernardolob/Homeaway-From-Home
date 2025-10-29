import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Tests {

    @Test public void test01() { test("input1","output1"); }
    @Test public void test02() { test("input2","output2"); }
    @Test public void test03() { test("input3","output3"); }
    @Test public void test04() { test("input4","output4"); }
//    @Test public void test05() { test("input5","output5"); }
//    @Test public void test06() { test("input6","output6"); }
//    @Test public void test07() { test("input7","output7"); }
//    @Test public void test08() { test("input8","output8"); }
//    @Test public void test09() { test("input9","output9"); }
//    @Test public void test10() { test("input10","output10"); }
//    @Test public void test11() { test("input11","output11"); }
//    @Test public void test12() { test("input12","output12"); }
//    @Test public void test13() { test("input13","output13"); }
//    @Test public void test14() { test("input14","output14"); }
//    @Test public void test15() { test("input15","output15"); }
//    @Test public void test16() { test("input16","output16"); }
//    @Test public void test17() { test("input17","output17"); }
//    @Test public void test18() { test("input18","output18"); }

    private static final File BASE = new File("tests");

    /**
     * The consoleStream variable is used to redirect the output of the program to the console.
     * The outContent variable is used to capture the output of the program.
     */
    private PrintStream consoleStream;
    /**
     * The outContent variable is used to capture the output of the program.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * The setup method is executed before each test.
     * It redirects the output of the program to the console and captures the output of the program.
     */
    @Before
    public void setup() {
        consoleStream = System.out;
        System.setOut(new PrintStream(outContent));
    }

    /**
     * The test method is used to perform a test.
     * It receives as input the name of the input file and the name of the output file.
     * It reads the input file and the output file, and then executes the program with the input file.
     * It compares the output of the program with the expected output.
     * @param intput the name of the input file
     * @param output the name of the output file
     */
    public void test(String intput, String output) {
        test(new File(BASE, intput), new File(BASE, output));
    }

    /**
     * The test method is used to perform a test.
     * @param input the input file
     * @param output the output file
     */
    public void test(File input, File output) {
        consoleStream.println("Testing!");
        consoleStream.println("Input: " + input.getAbsolutePath());
        consoleStream.println("Output: " + output.getAbsolutePath());

        String fullInput = "", fullOutput = "";
        try {
            fullInput = new String(Files.readAllBytes(input.toPath()));
            fullOutput = new String(Files.readAllBytes(output.toPath()));
            consoleStream.println("INPUT ============");
            consoleStream.println(new String(fullInput));
            consoleStream.println("OUTPUT ESPERADO =============");
            consoleStream.println(new String(fullOutput));
            consoleStream.println("OUTPUT =============");
        } catch(Exception e) {
            e.printStackTrace();
            fail("Erro a ler o ficheiro");
        }

        try {
            Locale.setDefault(Locale.US);
            System.setIn(new FileInputStream(input));
            Class<?> mainClass = Class.forName("Main");
            mainClass.getMethod("main", String[].class).invoke(null, new Object[] { new String[0] });
        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro no programa");
        } finally {
            byte[] outPrintBytes = outContent.toByteArray();
            consoleStream.println(new String(outPrintBytes));

            assertEquals(removeCarriages(fullOutput), removeCarriages(new String(outContent.toByteArray())));
        }
    }

    /**
     * The removeCarriages method is used to remove the carriage returns from a string.
     * @param s the string
     * @return the string without carriage returns
     */
    private static String removeCarriages(String s) {
        return s.replaceAll("\r\n", "\n");
    }

}