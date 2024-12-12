package BackendCode;

import java.io.Serializable;

public class Booking implements Serializable {
    private int ID;
    private Customer customer;
    private Car car;
    private long RentTime, ReturnTime; // stores System time when the Book() method is called

    public Booking() {}

    public Booking(int ID, Customer customer, Car car, long RentTime, long ReturnTime) {
        this.ID = ID;
        this.customer = customer;
        this.car = car;
        this.RentTime = RentTime;
        this.ReturnTime = ReturnTime;
    }

    // Getters and Setters
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
    public long getRentTime() { return RentTime; }
    public void setRentTime(long RentTime) { this.RentTime = RentTime; }
    public long getReturnTime() { return ReturnTime; }
    public void setReturnTime(long ReturnTime) { this.ReturnTime = ReturnTime; }

    @Override
    public String toString() {
        return "Booking{" + "ID=" + ID + ", \ncustomer=" + customer.toString() + ", \ncar=" + car.toString() + ", \nRentTime=" + RentTime + ", ReturnTime=" + ReturnTime + '}' + "\n";
    }

    // Business logic: Calculate bill for the booking
    public int calculateBill() {
        long totalTime = (ReturnTime - RentTime) / (1000 * 60 * 60); // in hours
        int rentPerHour = car.getRentPerHour();
        return totalTime > 0 ? (int)(rentPerHour * totalTime) : rentPerHour;
    }
}
