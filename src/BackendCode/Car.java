package BackendCode;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a car with attributes and related operations.
 */
public class Car implements Serializable {
    private int ID;
    private String Maker, Name, Colour, Type, Model, Condition, RegNo;
    private int SeatingCapacity, RentPerHour;
    private CarOwner carOwner;

    public Car() {}

    public Car(int ID, String Maker, String Name, String Colour, String Type, 
               int SeatingCapacity, String Model, String Condition, 
               String RegNo, int RentPerHour, CarOwner carOwner) {
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
        return "Car{" +
                "ID=" + ID +
                ", Maker='" + Maker + '\'' +
                ", Name='" + Name + '\'' +
                ", Colour='" + Colour + '\'' +
                ", Type='" + Type + '\'' +
                ", Model='" + Model + '\'' +
                ", Condition='" + Condition + '\'' +
                ", RegNo='" + RegNo + '\'' +
                ", RentPerHour=" + RentPerHour +
                ", CarOwner=" + carOwner + '}';
    }

    public void add() {
        ArrayList<Car> cars = Car.view();
        this.ID = cars.isEmpty() ? 1 : cars.get(cars.size() - 1).getID() + 1;
        cars.add(this);
        saveToFile(cars, "Car.ser");
    }

    public void update() {
        ArrayList<Car> cars = Car.view();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getID() == this.ID) {
                cars.set(i, this);
                break;
            }
        }
        saveToFile(cars, "Car.ser");
    }

    public void remove() {
        ArrayList<Car> cars = Car.view();
        cars.removeIf(c -> c.getID() == this.ID);
        saveToFile(cars, "Car.ser");
    }

    public static ArrayList<Car> view() {
        return readFromFile("Car.ser");
    }

    public static Car searchByID(int id) {
        return view().stream().filter(car -> car.getID() == id).findFirst().orElse(null);
    }

    public static ArrayList<Car> searchByName(String name) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : view()) {
            if (car.getName().equalsIgnoreCase(name)) {
                result.add(car);
            }
        }
        return result;
    }

    public static Car searchByRegNo(String regNo) {
        return view().stream().filter(car -> car.getRegNo().equalsIgnoreCase(regNo)).findFirst().orElse(null);
    }

    private static ArrayList<Car> readFromFile(String fileName) {
        ArrayList<Car> cars = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    cars.add((Car) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private static void saveToFile(ArrayList<Car> cars, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Car car : cars) {
                oos.writeObject(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
