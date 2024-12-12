package BackendCode;

import java.io.Serializable;

/**
 * Person class representing a person entity, handling basic information and operations.
 * Responsibility: Manage person data.
 */
public abstract class Person implements Serializable {

    protected int ID;
    protected String CNIC, Name, Contact_No;

    public Person() {
    }

    public Person(int ID, String CNIC, String Name, String Contact_No) {
        this.ID = ID;
        this.CNIC = CNIC;
        this.Name = Name;
        this.Contact_No = Contact_No;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String Contact_No) {
        this.Contact_No = Contact_No;
    }

    public abstract void Add();
    public abstract void Update();
    public abstract void Remove();

    @Override
    public String toString() {
        return "Person{" + "ID=" + ID + ", CNIC=" + CNIC + ", Name=" + Name + ", Contact_No=" + Contact_No + '}';
    }
}
