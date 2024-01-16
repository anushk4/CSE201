package QuesThree;

import java.util.*;

class IncorrectPincodeException extends Exception {
    public IncorrectPincodeException(String message) {
        super(message);
    }
}

public class Ques3 {
    public static int checkValidity(String pin) {
        if (pin.length() != 6) {
            return 1;
        } else if (pin.charAt(0) == '0') {
            return 2;
        } else {
            try {
                int pin_n = Integer.parseInt(pin);
            } catch (NumberFormatException e) {
                return 3;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws IncorrectPincodeException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter pin code: ");
            try {
                String pin = input.nextLine();
                int valid = checkValidity(pin);
                if (valid == 1) {
                    throw new IncorrectPincodeException("Enter 6 digit pin code only");
                } else if (valid == 2) {
                    throw new IncorrectPincodeException("Cannot use 0 in the beginning");
                } else if (valid == 3) {
                    throw new IncorrectPincodeException("Non numeric character used");
                } else {
                    System.out.println("Valid pin code");
                    break;
                }
            } catch (IncorrectPincodeException e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }
}