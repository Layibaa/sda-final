package BackendCode;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a Car entity with various attributes and provides CRUD operations.
 * Violation (V5): SRP Violation - Handles validation, file I/O, and business logic.
 * Solution: Split into separate classes (Car, CarRepository, CarValidator).
 */
public class Car implements Serializable {

    // Attributes representing Car details
    private int ID;
    private String Maker, Name, Colour, Type;
    int SeatingCapacity; // V1: Encapsulation Violation - Should be private with getter/setter
    String Model, Condition, RegNo; // V1: Same issue - Should be private
    private int RentPerHour;
    private CarOwner carOwner;

    // Default Constructor
    public Car() {}

    /**
     * Parameterized constructor for creating a Car object.
     */
    public Car(int ID, String Maker, String Name, String Colour, String Type, int SeatingCapacity, 
               String Model, String Condition, String RegNo, int RentPerHour, CarOwner carOwner) {
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

    // Getters and Setters for encapsulation (Fix for V1)
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getMaker() { return Maker; }
    public void setMaker(String Maker) { this.Maker = Maker; }

    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public String getColour() { return Colour; }
    public void setColour(String Colour) { this.Colour = Colour; }

    public int getSeatingCapacity() { return SeatingCapacity; }
    public void setSeatingCapacity(int SeatingCapacity) { this.SeatingCapacity = SeatingCapacity; }

    public String getModel() { return Model; }
    public void setModel(String Model) { this.Model = Model; }

    public String getCondition() { return Condition; }
    public void setCondition(String Condition) { this.Condition = Condition; }

    public String getRegNo() { return RegNo; }
    public void setRegNo(String RegNo) { this.RegNo = RegNo; }

    public int getRentPerHour() { return RentPerHour; }
    public void setRentPerHour(int RentPerHour) { this.RentPerHour = RentPerHour; }

    public CarOwner getCarOwner() { return carOwner; }
    public void setCarOwner(CarOwner carOwner) { this.carOwner = carOwner; }

    /**
     * Adds the current Car object to the file storage (Car.ser).
     * Violation (V5): SRP Violation - File I/O logic mixed with business logic.
     * Violation (V11): Hard-coded ID generation logic in the method.
     */
    public void Add() {
        ArrayList<Car> car = Car.View();

        // Auto-generating ID (V11: Hard-coded logic for ID generation)
        if (car.isEmpty()) {
            this.ID = 1;
        } else {
            this.ID = car.get(car.size() - 1).ID + 1;
        }

        car.add(this); // Adding current object to the list

        // Writing the updated list back to the file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Car.ser"))) {
            for (Car c : car) {
                outputStream.writeObject(c);
            }
        } catch (IOException ex) {
            System.out.println("Error saving car: " + ex.getMessage()); // V9: Lack of proper exception handling
        }
    }

    /**
     * Reads the car list from the file and returns it.
     * Violation (V5): SRP Violation - File I/O should be handled in a repository class.
     */
    public static ArrayList<Car> View() {
        ArrayList<Car> carList = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Car.ser"))) {
            while (true) {
                try {
                    Car car = (Car) inputStream.readObject();
                    carList.add(car);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error reading cars: " + ex.getMessage()); // V9: Poor exception handling
        }
        return carList;
    }

    /**
     * Searches for cars by name.
     * Violation (V7): Method Duplication - Similar logic exists in other search methods.
     */
    public static Car SearchByName(String name) {
        for (Car car : View()) {
            if (car.getName().equalsIgnoreCase(name)) {
                return car;
            }
        }
        return null;
    }

    /**
     * Searches for cars by ID.
     * Violation (V7): Method Duplication - Logic is repeated for different search criteria.
     */
    public static Car SearchByID(int id) {
        for (Car car : View()) {
            if (car.getID() == id) {
                return car;
            }
        }
        return null;
    }

    /**
     * Searches for cars by registration number.
     * Violation (V7): Method Duplication - Repeated logic; can be generalized.
     */
    public static Car SearchByRegNo(String regNo) {
        for (Car car : View()) {
            if (car.getRegNo().equalsIgnoreCase(regNo)) {
                return car;
            }
        }
        return null;
    }
}
