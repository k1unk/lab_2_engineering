import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TestConverter {

    public String test(String inputData) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        Converter.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        return byteArrayOutputStream.toString();
    }

    public void testFC(String inputData, String result) {
        String outputText = test(inputData);
        String key = "result:";
        String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testFToC() {
        testFC("f\n12\n", "-11.1");
        testFC("f\n-123.3\n", "-86.3");
        testFC("f\n0\n", "-17.8");
        testFC("f\n32\n", "0.0");
    }

    @Test
    public void testCToF() {
        testFC("c\n-11.1\n", "12.0");
        testFC("c\n-86.3\n", "-123.3");
        testFC("c\n-17.8\n", "0.0");
        testFC("c\n0\n", "32.0");
    }

    @Test
    public void incorrectInputLetter() {
        incorrectInputLetterHelper("d\nc\n0\n");
        incorrectInputLetterHelper("D\nZ\nF\n-12\n");
        incorrectInputLetterHelper("A\nf\n0\n");
    }

    public void incorrectInputLetterHelper(String inputData) {
        String outputText = test(inputData);
        String key = "error. No such mode (C or F)";
        Assert.assertTrue(outputText.contains(key));
    }

    @Test
    public void correctInputLetter() {
        correctInputLetterHelper("c\n0\n");
        correctInputLetterHelper("C\n0\n");
        correctInputLetterHelper("f\n0\n");
        correctInputLetterHelper("F\n0\n");
    }

    public void correctInputLetterHelper(String inputData) {
        String outputText = test(inputData);
        String key = "error. No such mode (C or F)";
        Assert.assertFalse(outputText.contains(key));
    }

    @Test
    public void incorrectInputNumber() {
        incorrectInputNumberHelper("c\nfd\n0\n");
        incorrectInputNumberHelper("F\n12d\n-12\n");
        incorrectInputNumberHelper("f\n \n0\n");
    }

    public void incorrectInputNumberHelper(String inputData) {
        String outputText = test(inputData);
        String key = "Wrong input value";
        Assert.assertTrue(outputText.contains(key));
    }


}