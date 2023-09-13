package org.sam;


	import java.util.Scanner;

	public class WeatherApp {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("Choose an option:");
	            System.out.println("1. Get Temperature");
	            System.out.println("2. Get Wind Speed");
	            System.out.println("3. Get Pressure");
	            System.out.println("0. Exit");

	            int choice = scanner.nextInt();

	            if (choice == 0) {
	                System.out.println("Terminating the program.");
	                break;
	            } else if (choice >= 1 && choice <= 3) {
	                System.out.print("Enter date with time (yyyy-MM-dd HH:mm:ss): ");
	    
	                String dateTime = scanner.nextLine();

	                switch (choice) {
	                    case 1:
	                        getTemperature(dateTime);
	                        break;
	                    case 2:
	                        getWindSpeed(dateTime);
	                        break;
	                    case 3:
	                        getPressure(dateTime);
	                        break;
	                }
	            } else {
	                System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }

	        scanner.close();
	    }

	    private static void getTemperature(String dateTime) {
	      System.out.println("Temperature at " + dateTime + ": [Temperature Value]°C");
	    }

	    private static void getWindSpeed(String dateTime) {
	     System.out.println("Wind Speed at " + dateTime + ": [Wind Speed Value] m/s");
	    }

	    private static void getPressure(String dateTime) {
	        System.out.println("Pressure at " + dateTime + ": [Pressure Value] hPa");
	    }
	}



