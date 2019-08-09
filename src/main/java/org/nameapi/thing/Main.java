package org.nameapi.thing;

import org.nameapi.thing.service.ThingInfoType;
import org.nameapi.thing.service.ThingService;
import org.nameapi.thing.service.impl.ThingServiceImpl;
import java.util.Scanner;

/**
 * Main demo class.
 */
public class Main {

    /**
     * Quit program command.
     */
    private static final String QUIT = "quit";

    /**
     * Presision for demo program.
     */
    private static final int PRECISION = 10;

    /**
     * Default encoding for input.
     */
    private static final String DEFAULT_ENCODING = "UTF-8";
    /**
     * Service instance.
     */
    private final ThingService service = new ThingServiceImpl(PRECISION);

    /**
     * Main demo work method.
     */
    public final void doWork() {
        System.out.println("Please offer numbers");
        System.out.println("   format:  '.' as decimal separator, "
                + "e - separate exponent in scientific notation");
        System.out.println("   one number in line");
        System.out.println("   calculation precision is "
                + PRECISION
                + " digits");
        System.out.println("type 'quit' to end program");
        Scanner in = new Scanner(System.in, DEFAULT_ENCODING);
        while (true) {
            String number = in.nextLine();
            if (QUIT.equals(number)) {
                break;
            } else {
                try {
                    service.addNumber(number);
                    System.out.println(String.format("MIN: %s, MAX: %s, "
                                    + "AVG: %s",
                            service.getThingInfo(ThingInfoType.MIN),
                            service.getThingInfo(ThingInfoType.MAX),
                            service.getThingInfo(ThingInfoType.AVERAGE)
                    ));
                } catch (IllegalArgumentException e) {
                    System.out.println("Incorrect number, ignored");
                }
            }
        }
        System.out.println("Program end.");
    }

    /**
     * Main entry point.
     * @param args - command line args, not used
     */
    public static void main(final String[] args) {
        Main main = new Main();
        main.doWork();
    }

}
