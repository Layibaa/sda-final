package BackendCode;

import java.io.*;
import java.util.ArrayList;

public class CarOwnerPersistence {

    private static final String FILE_PATH = "CarOwner.ser";

    // Add a new CarOwner to the file
    public static void addCarOwner(CarOwner carOwner) {
        ArrayList<CarOwner> carOwners = readCarOwners();
        carOwner.setID(carOwners.isEmpty() ? 1 : carOwners.get(carOwners.size() - 1).getID() + 1); // Auto ID Generation
        carOwners.add(carOwner);
        writeCarOwners(carOwners);
    }

    // Update a CarOwner in the file
    public static void updateCarOwner(CarOwner updatedCarOwner) {
        ArrayList<CarOwner> carOwners = readCarOwners();
        for (int i = 0; i < carOwners.size(); i++) {
            if (carOwners.get(i).getID() == updatedCarOwner.getID()) {
                carOwners.set(i, updatedCarOwner);
                break;
            }
        }
        writeCarOwners(carOwners);
    }

    // Remove a CarOwner from the file
    public static void removeCarOwner(CarOwner carOwner) {
        ArrayList<CarOwner> carOwners = readCarOwners();
        carOwners.removeIf(owner -> owner.getID() == carOwner.getID());
        writeCarOwners(carOwners);
    }

    // Read all CarOwners from the file
    private static ArrayList<CarOwner> readCarOwners() {
        ArrayList<CarOwner> carOwners = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            while (true) {
                try {
                    carOwners.add((CarOwner) inputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carOwners;
    }

    // Write all CarOwners to the file
    private static void writeCarOwners(ArrayList<CarOwner> carOwners) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            for (CarOwner carOwner : carOwners) {
                outputStream.writeObject(carOwner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Search CarOwners by name
    public static ArrayList<CarOwner> searchCarOwnersByName(String name) {
        ArrayList<CarOwner> carOwners = readCarOwners();
        ArrayList<CarOwner> result = new ArrayList<>();
        for (CarOwner carOwner : carOwners) {
            if (carOwner.getName().equalsIgnoreCase(name)) {
                result.add(carOwner);
            }
        }
        return result;
    }

    // Search CarOwner by ID
    public static CarOwner searchCarOwnerByID(int id) {
        ArrayList<CarOwner> carOwners = readCarOwners();
        for (CarOwner carOwner : carOwners) {
            if (carOwner.getID() == id) {
                return carOwner;
            }
        }
        return null;
    }

    // Search CarOwner by CNIC
    public static CarOwner searchCarOwnerByCNIC(String carOwnerCNIC) {
        ArrayList<CarOwner> carOwners = readCarOwners();
        for (CarOwner carOwner : carOwners) {
            if (carOwner.getCNIC().equalsIgnoreCase(carOwnerCNIC)) {
                return carOwner;
            }
        }
        return null;
    }
}
