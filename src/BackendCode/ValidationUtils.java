package BackendCode;

public class ValidationUtils {
    public static boolean isCNICValid(String cnic) {
        return cnic != null && cnic.matches("\\d{13}");
    }

    public static boolean isContactNoValid(String contact) {
        return contact != null && contact.matches("03\\d{9}");
    }

    public static boolean isNameValid(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    public static boolean isIDvalid(String id) {
        return id != null && id.matches("\\d+") && Integer.parseInt(id) > 0;
    }
}
