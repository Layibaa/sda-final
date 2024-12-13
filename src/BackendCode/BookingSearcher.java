package BackendCode;

import java.io.*;
import java.util.ArrayList;

public class BookingSearcher {
    private static final String FILE_NAME = "Booking.ser";

    public static ArrayList<Booking> SearchByCustomerID(int CustomerID) {
        ArrayList<Booking> bookingList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    if (myObj.getCustomer().getID() == CustomerID) {
                        bookingList.add(myObj);
                    }
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return bookingList;
    }

    public static ArrayList<Booking> SearchByCarRegNo(String CarRegNo) {
        ArrayList<Booking> bookingList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    if (myObj.getCar().getRegNo().equalsIgnoreCase(CarRegNo)) {
                        bookingList.add(myObj);
                    }
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return bookingList;
    }

    public static ArrayList<Booking> SearchByCarID(int carID) {
        ArrayList<Booking> bookingList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    if (myObj.getCar().getID() == carID) {
                        bookingList.add(myObj);
                    }
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return bookingList;
    }
}
