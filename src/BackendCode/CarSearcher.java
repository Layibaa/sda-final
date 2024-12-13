package BackendCode;

import java.util.ArrayList;

public class CarSearcher {

    // Search cars by name
    public static ArrayList<Car> SearchByName(String name) {
        ArrayList<Car> carList = Car.View();
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : carList) {
            if (car.getName().equalsIgnoreCase(name)) { // Use getName()
                result.add(car);
            }
        }
        return result;
    }

    // Search car by ID
    public static Car SearchByID(int id) {
        ArrayList<Car> carList = Car.View();
        for (Car car : carList) {
            if (car.getID() == id) { // Use getID()
                return car;
            }
        }
        return null;
    }

    // Search car by registration number
    public static Car SearchByRegNo(String regNo) {
        ArrayList<Car> carList = Car.View();
        for (Car car : carList) {
            if (car.getRegNo().equalsIgnoreCase(regNo)) { // Use getRegNo()
                return car;
            }
        }
        return null;
    }
}
