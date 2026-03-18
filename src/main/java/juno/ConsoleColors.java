package juno;

/**
 * Utility class for console color output using ANSI escape codes.
 * Provides color constants and a method to colorize text for terminal display.
 */
public class ConsoleColors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String WHITE = "\u001B[37m";

    /**
     * Wraps text with ANSI color codes for colored console output.
     *
     * @param text The text to colorize
     * @param colorCode The ANSI color code to apply
     * @return The colorized text with reset code appended
     */
    public static String colorize(String text, String colorCode) {
        return colorCode + text + ConsoleColors.RESET;
    }
}
