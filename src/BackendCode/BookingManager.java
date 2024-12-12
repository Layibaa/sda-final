package BackendCode;

import java.io.*;
import java.util.ArrayList;

public class BookingManager {

    private static final String FILE_PATH = "Booking.ser";

    // Add a new Booking to the file
    public static void addBooking(Booking booking) {
        ArrayList<Booking> bookings = readBookings();
        booking.setID(bookings.isEmpty() ? 1 : bookings.get(bookings.size() - 1).getID() + 1);
        bookings.add(booking);
        writeBookings(bookings);
    }

    // Update a Booking in the file
    public static void updateBooking(Booking updatedBooking) {
        ArrayList<Booking> bookings = readBookings();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getID() == updatedBooking.getID()) {
                bookings.set(i, updatedBooking);
                break;
            }
        }
        writeBookings(bookings);
    }

    // Remove a Booking from the file
    public static void removeBooking(Booking booking) {
        ArrayList<Booking> bookings = readBookings();
        bookings.removeIf(b -> b.getID() == booking.getID());
        writeBookings(bookings);
    }

    // Read all Bookings from the file
    private static ArrayList<Booking> readBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            while (true) {
                try {
                    bookings.add((Booking) inputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Write all Bookings to the file
    private static void writeBookings(ArrayList<Booking> bookings) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            for (Booking booking : bookings) {
                outputStream.writeObject(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Search Bookings by Customer ID
    public static ArrayList<Booking> searchBookingsByCustomerID(int customerID) {
        ArrayList<Booking> bookings = readBookings();
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getID() == customerID) {
                result.add(booking);
            }
        }
        return result;
    }

    // Search Bookings by Car Registration Number
    public static ArrayList<Booking> searchBookingsByCarRegNo(String carRegNo) {
        ArrayList<Booking> bookings = readBookings();
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCar().getRegNo().equalsIgnoreCase(carRegNo)) {
                result.add(booking);
            }
        }
        return result;
    }

    // Get all Booked Cars
    public static ArrayList<Car> getBookedCars() {
        ArrayList<Car> bookedCars = new ArrayList<>();
        ArrayList<Booking> bookings = readBookings();
        for (Booking booking : bookings) {
            if (booking.getReturnTime() == 0) {
                bookedCars.add(booking.getCar());
            }
        }
        return bookedCars;
    }

    // Get all Unbooked Cars
    public static ArrayList<Car> getUnbookedCars() {
        ArrayList<Car> allCars = CarManager.getAllCars(); // Assuming a CarManager class for car handling
        ArrayList<Car> bookedCars = getBookedCars();
        allCars.removeAll(bookedCars);
        return allCars;
    }
}
