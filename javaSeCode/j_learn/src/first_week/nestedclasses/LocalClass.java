package first_week.nestedclasses;

/**
 * ClassName: LocalClass
 * Package: first_week.nestedclasses
 * Description:
 *
 * @Author fly
 * @Create 2026/1/13 01:14
 * @Version 1.0
 */
public class LocalClass {
    static String regularExpression = "[^0-9]";
    private String phoneNumber;
    public static void validatePhoneNumber(String phoneNumber) {
        int numberLength = 11;
        class PhoneNumber {
            String formattedNumber = null;
            public PhoneNumber(String phoneNumber) {
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                if (currentNumber.length() == numberLength) {
                    formattedNumber = currentNumber;
                }
            }

            public String getNumber() {
                return formattedNumber;
            }
        }
        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber);
//        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber);

        if (myNumber1.getNumber() == null)
            System.out.println("First number is invalid");
        else
            System.out.println("First number is " + myNumber1.getNumber());
    }


    public static void main(String[] args) {
        validatePhoneNumber("12345678901");
    }
}
