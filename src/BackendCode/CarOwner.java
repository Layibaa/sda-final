package BackendCode;

import java.io.*;
import java.util.ArrayList;

public class CarOwner extends Person implements Serializable {

    private int Balance; // V18: Primitive Obsession - Balance should be encapsulated in a custom type.

    // Default constructor
    public CarOwner() {
        super();
    }

    // Parameterized constructor
    public CarOwner(int Balance, int ID, String CNIC, String Name, String Contact_No) {
        super(ID, CNIC, Name, Contact_No);
        this.Balance = Balance;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    @Override
    public String toString() {
        return super.toString() + " CarOwner{" + "Balance=" + Balance + '}' + "\n";
    }

    @Override
    public void Add() {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        if (carOwner.isEmpty()) {
            this.ID = 1; // V19: Magic Number - Hardcoded ID initialization logic.
        } else {
            this.ID = carOwner.get(carOwner.size() - 1).ID + 1; // V19: Magic logic for ID generation.
        }
        carOwner.add(this);

        // File I/O Logic - SRP Violation (V12), Duplicate Logic (V15), Hard-Coded Path (V13)
        File file = new File("CarOwner.ser"); // V13: Hardcoded file name.
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex); // V14: Improper Exception Logging.
            }
        }
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (CarOwner owner : carOwner) {
                outputStream.writeObject(owner); // V15: Duplicate file writing logic.
            }
        } catch (IOException ex) {
            System.out.println(ex); // V14: Exceptions are not logged properly.
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close(); // V17: Resource handling - Should use try-with-resources.
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    @Override
    public void Update() {
        ArrayList<CarOwner> carOwner = CarOwner.View();

        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).ID == ID) {
                carOwner.set(i, this); // Logic to replace object
            }
        }

        // V12, V13, V14, V15: SRP Violation, Hardcoded file path, Duplicate code.
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CarOwner.ser"))) {
            for (CarOwner owner : carOwner) {
                outputStream.writeObject(owner);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void Remove() {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).ID == ID) {
                carOwner.remove(i); // Remove logic
            }
        }

        // V12, V13, V15: SRP Violation, Hardcoded path, Duplicate code.
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CarOwner.ser"))) {
            for (CarOwner owner : carOwner) {
                outputStream.writeObject(owner);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // V16: Duplicate search logic. Can be generalized using Predicate<CarOwner>.
    public static ArrayList<CarOwner> SearchByName(String name) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        ArrayList<CarOwner> result = new ArrayList<>();
        for (CarOwner owner : carOwner) {
            if (owner.Name.equalsIgnoreCase(name)) {
                result.add(owner);
            }
        }
        return result;
    }

    public static CarOwner SearchByCNIC(String carOwnerCNIC) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (CarOwner owner : carOwner) {
            if (owner.CNIC.equalsIgnoreCase(carOwnerCNIC)) {
                return owner;
            }
        }
        return null;
    }

    public static CarOwner SearchByID(int id) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (CarOwner owner : carOwner) {
            if (owner.ID == id) {
                return owner;
            }
        }
        return null;
    }

    public static ArrayList<CarOwner> View() {
        ArrayList<CarOwner> carOwnerList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("CarOwner.ser"))) {
            boolean EOF = false;
            while (!EOF) {
                try {
                    CarOwner owner = (CarOwner) inputStream.readObject();
                    carOwnerList.add(owner);
                } catch (EOFException end) {
                    EOF = true;
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e); // V14: Exception not logged properly.
        }
        return carOwnerList;
    }
}
