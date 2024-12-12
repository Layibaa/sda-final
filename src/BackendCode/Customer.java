package BackendCode;

import java.io.Serializable;

/**
 * Customer class representing customer entity and their information.
 * Responsibility: Manage customer data (ID, Name, CNIC, Contact_No, and Bill).
 */
public class Customer extends Person implements Serializable {

    private int Bill;

    public Customer() {
        super();
    }

    public Customer(int Bill, int ID, String CNIC, String Name, String Contact_No) {
        super(ID, CNIC, Name, Contact_No);
        this.Bill = Bill;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int Bill) {
        this.Bill = Bill;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" + "Bill=" + Bill + '}';
    }
}
