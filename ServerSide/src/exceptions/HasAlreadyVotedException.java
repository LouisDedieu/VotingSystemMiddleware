package exceptions;

public class HasAlreadyVotedException extends Exception{
    public HasAlreadyVotedException(String message) {
        super(message);
    }
}
