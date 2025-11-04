package util;
import exception.InvalidInputException;

public class InputValidator {
    public static void validateCity(String city) throws InvalidInputException {
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidInputException("City name cannot be empty.");
        }
    }
}