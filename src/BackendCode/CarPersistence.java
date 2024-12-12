package BackendCode;

import java.io.*;
import java.util.ArrayList;

public class CarPersistence {

    private static final String FILE_PATH = "Car.ser";

    // Read cars from file
    public static ArrayList<Car> readCars() {
        ArrayList<Car> carList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            while (true) {
                try {
                    carList.add((Car) inputStream.readObject());
                } catch (EOFException e) {
                    break;  // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading cars: " + e.getMessage());
        }
        return carList;
    }

    // Write cars to file
    public static void writeCars(ArrayList<Car> cars) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            for (Car car : cars) {
                outputStream.writeObject(car);
            }
        } catch (IOException e) {
            System.out.println("Error writing cars: " + e.getMessage());
        }
    }
}
