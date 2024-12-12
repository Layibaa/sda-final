package BackendCode;

/**
 * Validator class responsible for performing various validation checks.
 * Responsibility: Handle all validation logic separately.
 */
public class Validator {

    /**
     * A valid CNIC consists of 13 characters, only digits.
     * @param cnic, The CNIC whose validity is to be checked
     * @return true if the passed CNIC is valid 
     */
    public static boolean isCNICValid(String cnic) {
        if (cnic.length() != 13) return false;
        for (int i = 0; i < cnic.length(); i++) {
            if (!Character.isDigit(cnic.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * A valid Contact No. has 11 digits and starts with "03"
     * @param contact 
     * @return true if the contact is valid 
     */
    public static boolean isContactNoValid(String contact) {
        if (contact.length() != 11 || !contact.startsWith("03")) {
            return false;
        }
        for (int i = 0; i < contact.length(); i++) {
            if (!Character.isDigit(contact.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * A valid name can contain only letters and white spaces
     * @param Name
     * @return true if the name is valid
     */
    public static boolean isNameValid(String Name) {
        for (int i = 0; i < Name.length(); i++) {
            if (!Character.isLetter(Name.charAt(i)) && Name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * A valid ID can only be digits greater than 0
     * @param ID
     * @return true if the ID is valid
     */
    public static boolean isIDvalid(String ID) {
        for (int i = 0; i < ID.length(); i++) {
            if (!Character.isDigit(ID.charAt(i))) {
                return false;
            }
        }
        if (Integer.parseInt(ID) <= 0) {
            return false;
        }
        return true;
    }
}
