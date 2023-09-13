package org.sam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;



public class WeatherApp {
	private static final String API_URL = "https://api.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=140145e6d9602e860024e9275cc8493f";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Get Temperature");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    getTemperature(scanner);
                    break;
                case 2:
                    getWindSpeed(scanner);
                    break;
                case 3:
                    getPressure(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void getTemperature(Scanner scanner) {
        System.out.print("Enter date with time (yyyy-MM-dd HH:mm:ss):");
        String inputDate = scanner.nextLine();
     try {
            JSONObject weatherData = fetchDataFromAPI();
           JSONArray hourlyForecasts = (JSONArray) weatherData.get("list");
           SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           for (int i = 0; i < hourlyForecasts.length(); i++) {
                JSONObject forecast = hourlyForecasts.getJSONObject(i);
                String dt_txt = (String) forecast.getString("dt_txt");
               java.util.Date forecastDate = inputFormat.parse(dt_txt);


               if (inputDate.equals(inputFormat.format(forecastDate))) {
                    JSONObject main = (JSONObject) forecast.getJSONObject("main");
                    double temperature = (double) main.getDouble("temp");

                    System.out.println("Temperature at " + inputDate + " : " + temperature + "°C");
                    return;
                }
            }

            System.out.println("Weather data not found for the given date and time.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
}

    private static void getWindSpeed(Scanner scanner) {
  
    }

    private static void getPressure(Scanner scanner) {
    }

    private static JSONObject fetchDataFromAPI() throws IOException, ParseException, org.json.simple.parser.ParseException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(response.toString());
    }

}
