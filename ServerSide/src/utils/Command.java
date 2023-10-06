package utils;

public enum Command {
    START, STOP, EXIT, UNKNOWN;

    public static Command fromString(String commandString) {
        try {
            return Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
