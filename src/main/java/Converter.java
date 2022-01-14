import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Converter {

    private final Scanner scanner;
    private final PrintStream printStream;

    public Converter(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public void start() {
        while (true) {
            printStream.println("F to C (print 'F') or C to F (print 'C') ?");
            String mode = scanner.nextLine();
            if (mode.equals("C") || mode.equals("c")) {
                cToF(scanner, printStream);
                break;
            } else if (mode.equals("F") || mode.equals("f")) {
                fToC(scanner, printStream);
                break;
            } else {
                printStream.println("error. No such mode (C or F)");
            }
        }
    }

    public static void main(String[] args) {
        Converter userInputExample = new Converter(System.in, System.out);
        userInputExample.start();
    }

    static void cToF(Scanner scanner, PrintStream printStream) {
        double result =Math.round((getTemperature(scanner, printStream) * 9.0 / 5.0 + 32.0)*10.0)/10.0;
        printStream.println("result: " + result);
    }

    static void fToC(Scanner scanner, PrintStream printStream) {
        double result = Math.round((getTemperature(scanner, printStream) - 32.0) * 5.0 / 9.0*10.0)/10.0;
        printStream.println("result: " + result);
    }

    static double getTemperature(Scanner scanner, PrintStream printStream) {
        double temperature;
        while (true) {
            printStream.println("Print value:");
            String value = scanner.nextLine();
            try {
                if (value.contains(".")) {
                    temperature = Double.parseDouble(value);
                } else {
                    temperature = Integer.parseInt(value);
                }
                break;
            } catch (Exception e) {
                printStream.println("Wrong input value");
            }
        }
        return temperature;
    }
}
