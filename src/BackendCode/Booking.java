package BackendCode;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author @AbdullahShahid01
 */
public class Booking implements Serializable {

    private int ID; // Unique identifier for the booking
    private Customer customer; // Customer details
    private Car car; // Car details
    private long RentTime, ReturnTime; // Rent and return time

    public Booking() {
    }

    public Booking(int ID, Customer customer, Car car, long RentTime, long ReturnTime) {
        this.ID = ID;
        this.customer = customer;
        this.car = car;
        this.RentTime = RentTime;
        this.ReturnTime = ReturnTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public long getRentTime() {
        return RentTime;
    }

    public void setRentTime(long RentTime) {
        this.RentTime = RentTime;
    }

    public long getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(long ReturnTime) {
        this.ReturnTime = ReturnTime;
    }

    @Override
    public String toString() {
        // V1: Violation of SRP (Single Responsibility Principle)
        // The toString method accesses customer and car's toString methods.
        return "Booking{" + "ID=" + ID 
                + ", \ncustomer=" + customer.toString() 
                + ", \ncar=" + car.toString() 
                + ", \nRentTime=" + RentTime 
                + ", ReturnTime=" + ReturnTime + '}' + "\n";
    }

    public void Add() {
        ArrayList<Booking> booking = Booking.View();
        if (booking.isEmpty()) {
            this.ID = 1;
        } else {
            this.ID = booking.get(booking.size() - 1).ID + 1; // Auto ID ...
        }
        this.ReturnTime = 0;
        booking.add(this);
        File file = new File("Booking.ser");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                // V2: Violation - Improper Exception Handling
                // Exception should be properly logged or handled, not just printed.
                System.out.println(ex);
            }
        }
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (int i = 0; i < booking.size(); i++) {
                outputStream.writeObject(booking.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public void Update() {
        ArrayList<Booking> booking = Booking.View();
        for (int i = 0; i < booking.size(); i++) {
            if (booking.get(i).ID == ID) {
                booking.set(i, this);
            }
        }

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Booking.ser"));
            for (int i = 0; i < booking.size(); i++) {
                outputStream.writeObject(booking.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public void Remove() {
        ArrayList<Booking> booking = Booking.View();
        for (int i = 0; i < booking.size() - 1; i++) {
            if ((booking.get(i).ID == ID)) {
                for (int j = i; j < booking.size() - 1; j++) {
                    booking.set(j, (booking.get(j + 1))); // V3: Violation of Data Structure Misuse
                    // Repeated manual shifting mimics array logic.
                }
            }
        }

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Booking.ser"));
            for (int i = 0; i < booking.size() - 1; i++) {
                outputStream.writeObject(booking.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public int calculateBill() {
        long rentTime = this.getRentTime();
        long returnTime = this.getReturnTime();
        long totalTime = returnTime - rentTime;
        totalTime = totalTime / (1000 * 60 * 60);

        int rentPerHour = this.getCar().getRentPerHour();
        if (totalTime != 0) {
            return (int) (rentPerHour * totalTime);
        } else {
            return rentPerHour; // V4: Violation - Magic Number
            // Unclear handling of zero rentTime.
        }
    }

    // More methods with similar issues ...
}
