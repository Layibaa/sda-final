/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendCode;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class CarOwnerSearcher extends CarOwner {
    public static ArrayList<CarOwner> SearchByName(String name) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        ArrayList<CarOwner> s = new ArrayList<>();

        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).Name.equalsIgnoreCase(name)) {
                s.add(carOwner.get(i));
            }
        }
        return s;
    }

    public static CarOwner SearchByCNIC(String carOwnerCNIC) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).CNIC.equalsIgnoreCase(carOwnerCNIC)) {
                return carOwner.get(i);
            }
        }
        return null;
    }

    public static CarOwner SearchByID(int id) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).ID == id) {
                return carOwner.get(i);
            }
        }
        return null;
    }

}
