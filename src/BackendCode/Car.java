package BackendCode;

import java.io.*;
import java.util.ArrayList;

/**
 * The Car class holds car attributes and implements business logic related to car data.
 * Refactored to adhere to SOLID principles.
 */
public class Car implements Serializable {

    // Car Attributes
    private int ID;
    private String Maker, Name, Colour, Type;
    private int SeatingCapacity;
    private String Model, Condition, RegNo;
    private int RentPerHour;
    private CarOwner carOwner;

    // Constants to replace magic numbers/strings
    private static final String CAR_FILE_PATH = "Car.ser";
    private static final String REGEX_REG_NO = "^[A-Za-z]+-\\d+$";  // RegNo format: letters-digits

    // Default Constructor
    public Car() {}

    // Parametrized Constructor
    public Car(int ID, String Maker, String Name, String Colour, String Type, int SeatingCapacity, String Model, String Condition, String RegNo, int RentPerHour, CarOwner carOwner) {
        this.ID = ID;
        this.Maker = Maker;
        this.Name = Name;
        this.Colour = Colour;
        this.Type = Type;
        this.SeatingCapacity = SeatingCapacity;
        this.Model = Model;
        this.Condition = Condition;
        this.RegNo = RegNo;
        this.RentPerHour = RentPerHour;
        this.carOwner = carOwner;
    }

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String Maker) {
        this.Maker = Maker;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String Colour) {
        this.Colour = Colour;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getSeatingCapacity() {
        return SeatingCapacity;
    }

    public void setSeatingCapacity(int SeatingCapacity) {
        this.SeatingCapacity = SeatingCapacity;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }

    public String getRegNo() {
        return RegNo;
    }

    public void setRegNo(String RegNo) {
        this.RegNo = RegNo;
    }

    public int getRentPerHour() {
        return RentPerHour;
    }

    public void setRentPerHour(int RentPerHour) {
        this.RentPerHour = RentPerHour;
    }

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }

    @Override
    public String toString() {
        return "Car{" + "ID=" + ID + ", Maker=" + Maker + ", Name=" + Name + ", Colour=" + Colour + ", Type=" + Type + ", SeatingCapacity=" + SeatingCapacity + ", Model=" + Model + ", Condition=" + Condition + ", RegNo=" + RegNo + ", RentPerHour=" + RentPerHour + ", carOwner=" + carOwner + '}';
    }

    // Refactored Add Method
    public void Add() {
        ArrayList<Car> cars = CarPersistence.readCars();
        this.ID = (cars.isEmpty()) ? 1 : cars.get(cars.size() - 1).ID + 1;  // Auto ID Generation
        cars.add(this);
        CarPersistence.writeCars(cars);  // Externalize file writing
    }

    // Refactored Update Method
    public void Update() {
        ArrayList<Car> cars = CarPersistence.readCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).ID == ID) {
                cars.set(i, this);  // Replace car with matching ID
                break;
            }
        }
        CarPersistence.writeCars(cars);  // Externalize file writing
    }

    // Refactored Remove Method
    public void Remove() {
        ArrayList<Car> cars = CarPersistence.readCars();
        cars.removeIf(car -> car.ID == this.ID);  // Simplified removal using removeIf
        CarPersistence.writeCars(cars);  // Externalize file writing
    }

    // Search Methods
    public static ArrayList<Car> SearchByName(String name) {
        ArrayList<Car> cars = CarPersistence.readCars();
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.Name.equalsIgnoreCase(name)) {
                result.add(car);
            }
        }
        return result;
    }

    public static Car SearchByID(int id) {
        ArrayList<Car> cars = CarPersistence.readCars();
        for (Car car : cars) {
            if (car.ID == id) {
                return car;
            }
        }
        return null;
    }

    public static Car SearchByRegNo(String regNo) {
        ArrayList<Car> cars = CarPersistence.readCars();
        for (Car car : cars) {
            if (car.RegNo.equalsIgnoreCase(regNo)) {
                return car;
            }
        }
        return null;
    }

    // Validation Methods
    public static boolean isNameValid(String name) {
        return name != null && name.matches("[A-Za-z0-9 ]+");  // Improved regex for valid names
    }

    public static boolean isRegNoValid(String regNo) {
        return regNo != null && regNo.matches(REGEX_REG_NO);  // Improved RegNo validation
    }

    // Rented Check
    public boolean isRented() {
        ArrayList<Car> bookedCars = Booking.getBookedCars();
        for (Car car : bookedCars) {
            if (car.ID == this.ID) {
                return true;
            }
        }
        return false;
    }
}
