package controller;
import service.WeatherService;
import util.InputValidator;
import exception.InvalidInputException;
import java.util.Scanner;

public class WeatherController {
    private final WeatherService service = new WeatherService();

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = sc.nextLine();

        try {
            InputValidator.validateCity(city);
            service.showWeather(city);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}