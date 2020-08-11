package infrastructure;

public class InvalidLineException extends Throwable {

    public InvalidLineException(String type, String badValue) {
        super(String.format("Error reading file during %s line: %s", type, badValue));
    }
}
